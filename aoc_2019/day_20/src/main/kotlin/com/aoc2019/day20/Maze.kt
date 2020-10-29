package com.aoc2019.day20

import com.aoc2019.common.math.Vec2i
import java.io.IOException
import java.util.*
import kotlin.collections.HashSet

data class Maze(
        val walls: Map<Vec2i, Wall>,
        val openSpaces: Map<Vec2i, OpenSpace>,
        val portals: Set<Portal>,
        val start: OpenSpace,
        val destination: OpenSpace
) {

    val adjacentOpenSpaces: Map<OpenSpace, Set<OpenSpace>> = openSpaces
            .values
            .map { space ->
                val spaces = space.position.adjacentManhattanPositions().mapNotNull { openSpaces[it] }
                val spacesThroughPortals = portals
                        .filter { it.nextTo == space }
                        .map { portal ->
                            portals.filter { it != portal }
                                    .find { it.id == portal.id }!!
                                    .nextTo
                        }

                space to (spaces + spacesThroughPortals).toSet()
            }
            .toMap()

    fun calculateShortestRoute(): Int {
        val openStates = PriorityQueue<VisitedSpace>(Comparator.comparingInt { it.steps })
        openStates.add(VisitedSpace(start, 0))

        val closedStates = HashSet<OpenSpace>()

        while (openStates.isNotEmpty()) {
            val current = openStates.remove()
            if (current.space in closedStates) {
                continue
            }

            closedStates.add(current.space)

            if (current.space == destination) {
                return current.steps
            }

            openStates.addAll(adjacentOpenSpaces.getValue(current.space)
                    .filter { it !in closedStates }
                    .map { VisitedSpace(it, current.steps + 1) }
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

            return openSpaces.values
                    .flatMap { space -> space.position.adjacentManhattanPositions().map { space to it } }
                    .filter { (_, adjacent) -> adjacent !in openSpaces }
                    .filter { (_, adjacent) -> adjacent !in walls }
                    .map { (openSpace, portalPos) ->
                        val id = when (portalPos) {
                            openSpace.position.up() -> charMap.getValue(portalPos.up()).toString() + charMap.getValue(portalPos)
                            openSpace.position.down() -> charMap.getValue(portalPos).toString() + charMap.getValue(portalPos.down())
                            openSpace.position.left() -> charMap.getValue(portalPos.left()).toString() + charMap.getValue(portalPos)
                            openSpace.position.right() -> charMap.getValue(portalPos).toString() + charMap.getValue(portalPos.right())
                            else -> throw IOException("Unable to determine portal ID")
                        }

                        Portal(id, openSpace)
                    }
                    .toSet()
        }

    }

}

