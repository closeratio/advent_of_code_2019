package com.aoc2019.day20

import com.aoc2019.common.math.Vec2i
import com.aoc2019.day20.PortalType.INNER
import com.aoc2019.day20.PortalType.OUTER
import java.io.IOException
import java.util.*
import kotlin.collections.HashSet

open class Maze(
        val walls: Map<Vec2i, Wall>,
        val openSpaces: Map<Vec2i, OpenSpace>,
        val portals: Set<Portal>,
        val start: OpenSpace,
        val destination: OpenSpace
) {

    val adjacentSpaces: Map<OpenSpace, Set<OpenSpace>> = openSpaces
            .values
            .map { space ->
                space to space.position
                        .adjacentManhattanPositions()
                        .mapNotNull { openSpaces[it] }
                        .toSet()
            }
            .toMap()

    val adjacentSpacesThroughPortals: Map<OpenSpace, Set<Portal>> = openSpaces
            .values
            .associateWith { space ->
                portals
                        .filter { it.nextTo == space }
                        .map { portal ->
                            portals.filter { it != portal }
                                    .find { it.id == portal.id }!!
                        }
                        .toSet()
            }

    fun calculateShortestRoute(
            recursive: Boolean = false
    ): Int {
        val openStates = PriorityQueue<VisitedSpace>(Comparator.comparingInt { it.steps })
        openStates.add(VisitedSpace(start, 0, 0))

        val closedStates = HashSet<VisitedSpace>()

        while (openStates.isNotEmpty()) {
            val current = openStates.remove()
            if (current in closedStates) {
                continue
            }

            if (current.level >= 1000) {
                throw IllegalStateException("Passed threshold which suggests no route exists")
            }

            closedStates.add(current)

            if (current.space == destination && current.level == 0) {
                return current.steps
            }

            openStates.addAll(adjacentSpaces
                    .getValue(current.space)
                    .map { VisitedSpace(it, current.steps + 1, current.level) }
                    .filter { it !in closedStates }
            )

            openStates.addAll(adjacentSpacesThroughPortals
                    .getOrDefault(current.space, setOf())
                    .map { portal ->
                        if (recursive) {
                            val newLevel = if (portal.type == INNER) current.level - 1 else current.level + 1
                            VisitedSpace(portal.nextTo, current.steps + 1, newLevel)
                        } else {
                            VisitedSpace(portal.nextTo, current.steps + 1, current.level)
                        }
                    }
                    .filter { it !in closedStates }
                    .filter { it.level >= 0 }
            )
        }

        throw IllegalStateException("Unable to find path from $start to $destination")
    }

    companion object {

        fun from(input: String): Maze {
            val walls = findObject(input, '#') { Wall(it) }
            val openSpaces = findObject(input, '.') { OpenSpace(it) }
            val portals = findPortals(input, openSpaces, walls)
            val start = portals.find { it.id == "AA" }!!
            val end = portals.find { it.id == "ZZ" }!!

            return Maze(
                    walls,
                    openSpaces,
                    portals.filter { it != start && it != end }.toSet(),
                    start.nextTo,
                    end.nextTo
            )
        }

        private fun <T> findObject(
                input: String,
                symbol: Char,
                builder: (Vec2i) -> T
        ): Map<Vec2i, T> = input
                .split("\n")
                .flatMapIndexed { y, line ->
                    line.mapIndexedNotNull { x, character ->
                        if (character == symbol) {
                            val vec = Vec2i(x, y)
                            vec to builder(vec)
                        } else {
                            null
                        }
                    }
                }
                .toMap()

        private fun findPortals(
                input: String,
                openSpaces: Map<Vec2i, OpenSpace>,
                walls: Map<Vec2i, Wall>
        ): Set<Portal> {
            val charMap: Map<Vec2i, Char> = input
                    .split("\n")
                    .flatMapIndexed { y, line ->
                        line.mapIndexed { x, character ->
                            Vec2i(x, y) to character
                        }
                    }
                    .toMap()

            val minMaxX = setOf(
                    openSpaces.keys.minByOrNull { it.x }!!.x,
                    openSpaces.keys.maxByOrNull { it.x }!!.x
            )

            val minMaxY = setOf(
                    openSpaces.keys.minByOrNull { it.y }!!.y,
                    openSpaces.keys.maxByOrNull { it.y }!!.y
            )

            return openSpaces.values
                    .flatMap { space -> space.position.adjacentManhattanPositions().map { space to it } }
                    .filter { (_, adjacent) -> adjacent !in openSpaces }
                    .filter { (_, adjacent) -> adjacent !in walls }
                    .map { (openSpace, portalPos) ->
                        val pos = openSpace.position
                        val id = when (portalPos) {
                            pos.up() -> charMap.getValue(portalPos.up()).toString() + charMap.getValue(portalPos)
                            pos.down() -> charMap.getValue(portalPos).toString() + charMap.getValue(portalPos.down())
                            pos.left() -> charMap.getValue(portalPos.left()).toString() + charMap.getValue(portalPos)
                            pos.right() -> charMap.getValue(portalPos).toString() + charMap.getValue(portalPos.right())
                            else -> throw IOException("Unable to determine portal ID")
                        }

                        Portal(
                                id,
                                openSpace,
                                if (pos.x in minMaxX || pos.y in minMaxY) OUTER else INNER
                        )
                    }
                    .toSet()
        }

    }


}

