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
            val route = calculateRoute(target)

            // Work out direction to move in
            val nextPosition = route.first()
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
    }

    private fun calculateRoute(target: Vec2i): List<Vec2i> {
        TODO()
    }

}

