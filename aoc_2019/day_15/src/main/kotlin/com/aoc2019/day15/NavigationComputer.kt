package com.aoc2019.day15

import com.aoc2019.common.computer.Computer
import com.aoc2019.common.math.Vec2i
import com.aoc2019.common.math.Vec2i.Companion.ZERO
import com.aoc2019.day15.Direction.*
import com.aoc2019.day15.PositionType.*

class NavigationComputer(
        val computer: Computer,
        val knownPositions: MutableMap<Vec2i, PositionType> = hashMapOf(
                ZERO to OPEN_SPACE
        ),
        var currentPosition: Vec2i = ZERO
) {

    init {
        updateSurroundingSpaces()
    }

    private fun updateSurroundingSpaces() {
        knownPositions.putAll(currentPosition.adjacentManhattanPositions()
                .filter { it !in knownPositions }
                .map { it to UNKNOWN })
    }

    fun explore() {
        val posTypeMap = PositionType.values().associateBy { it.code }

        // Keep exploring until there are no unknown tiles
        while (knownPositions.values.contains(UNKNOWN)) {
            // Get nearest unknown space
            val target = knownPositions.filter { (_, type) -> type == UNKNOWN }
                    .map { it.key }
                    .sortedBy { it.manhattan(currentPosition) }
                    .first()

            // Calculate route to target
            val route = calculateRoute(currentPosition, target)

            // Work out direction to move in
            val nextPosition = route.drop(1).first()
            val moveDirection = when {
                nextPosition.x < currentPosition.x -> WEST
                nextPosition.x > currentPosition.x -> EAST
                nextPosition.y < currentPosition.y -> NORTH
                nextPosition.y > currentPosition.y -> SOUTH
                else -> throw IllegalStateException("Can't determine movement direction from $currentPosition to $nextPosition")
            }

            // Issue movement command
            computer.inputValues.add(moveDirection.code)
            computer.iterateUntilFinishedOrWaiting()

            // Record result
            val result = posTypeMap.getValue(computer.outputs.poll())
            when (result) {
                WALL -> knownPositions[nextPosition] = WALL
                OPEN_SPACE, OXYGEN_SYSTEM -> {
                    currentPosition = nextPosition
                    knownPositions[currentPosition] = result
                }
            }

            // Update map based on current position
            updateSurroundingSpaces()
        }

        printKnownPositions()
    }

    fun calculateRoute(start: Vec2i, destination: Vec2i): List<Vec2i> {
        val openSet = start.adjacentManhattanPositions()
                .filter { it in knownPositions }
                .filter { knownPositions.getValue(it) != WALL }
                .let {
                    LinkedHashSet(it)
                }
        val closedSet = mutableSetOf(start)

        val previousMap = start.adjacentManhattanPositions()
                .filter { it in openSet }
                .associateWith { start }
                .toMutableMap()

        while (openSet.isNotEmpty()) {
            val curr = openSet.first()
            openSet.remove(curr)

            if (curr == destination) {
                return buildRoute(start, curr, previousMap)
            }

            closedSet.add(curr)
            curr.adjacentManhattanPositions()
                    .filter { it !in closedSet }
                    .filter { it !in openSet }
                    .filter { it in knownPositions }
                    .filter { knownPositions.getValue(it) != WALL }
                    .forEach {
                        openSet.add(it)
                        previousMap[it] = curr
                    }
        }

        throw IllegalStateException("Can't find route from $start to $destination")
    }

    private fun buildRoute(start: Vec2i, destination: Vec2i, previousMap: Map<Vec2i, Vec2i>): List<Vec2i> {
        val route = arrayListOf(destination)

        var curr = destination
        while (curr != start) {
            val next = previousMap.getValue(curr)
            route.add(next)
            curr = next
        }

        return route.reversed()
    }

    fun timeToFillWithOxygen(): Long {
        val positions = HashMap(knownPositions)

        var minutes = 0L
        while (positions.containsValue(OPEN_SPACE)) {
            val oxPositions = positions.filter { it.value == OXYGEN_SYSTEM }
            oxPositions.keys
                    .flatMap { it.adjacentManhattanPositions() }
                    .toSet()
                    .filter { it in knownPositions }
                    .filter { knownPositions.getValue(it) == OPEN_SPACE }
                    .forEach {
                        positions[it] = OXYGEN_SYSTEM
                    }


            minutes++
        }

        return minutes
    }

    private fun printKnownPositions(
    ) {
        val minX = knownPositions.keys.minBy { it.x }!!.x
        val maxX = knownPositions.keys.maxBy { it.x }!!.x
        val minY = knownPositions.keys.minBy { it.y }!!.y
        val maxY = knownPositions.keys.maxBy { it.y }!!.y

        val string = (minY.rangeTo(maxY)).map { y ->
            (minX.rangeTo(maxX)).map { x ->
                val pos = Vec2i.from(x, y)
                when (pos) {
                    ZERO -> "0"
                    currentPosition -> "C"
                    else -> {
                        when (knownPositions[pos]) {
                            WALL -> "#"
                            OXYGEN_SYSTEM -> "O"
                            OPEN_SPACE -> " "
                            UNKNOWN -> "U"
                            else -> "."
                        }
                    }
                }
            }.joinToString("")
        }.joinToString("\n")

        println(string)
    }

}

