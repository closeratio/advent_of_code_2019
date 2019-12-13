package com.aoc2019.day3

import com.aoc2019.common.math.Vec2i
import com.aoc2019.day3.Direction.*

class Circuit(
        val firstWirePositions: List<Vec2i>,
        val secondWirePositions: List<Vec2i>
) {

    fun intersectionPoints(): Set<Vec2i> = firstWirePositions
            .toSet()
            .intersect(secondWirePositions.toSet())

    fun closestIntersection(): Vec2i = intersectionPoints()
            .filter { it != Vec2i.ZERO }
            .minBy { it.manhattan(Vec2i.ZERO) }!!

    fun lowestStepsToIntersection(): Int {
        val intersections = intersectionPoints()
                .filter { it != Vec2i.ZERO }
                .toSet()

        val firstWireIntersectionValues = firstWirePositions
                .mapIndexed { step, position -> position to step }
                .filter { it.first in intersections }
                // Sort descending so that first instance of intersection is held in output map
                // (later instances are added first and are replaced by the earlier instances)
                .sortedByDescending { it.second }
                .toMap()

        val secondWireIntersectionValues = secondWirePositions
                .mapIndexed { step, position -> position to step }
                .filter { it.first in intersections }
                // Sort descending so that first instance of intersection is held in output map
                // (later instances are added first and are replaced by the earlier instances)
                .sortedByDescending { it.second }
                .toMap()

        return firstWireIntersectionValues
                .map { (position, value) ->
                    value + secondWireIntersectionValues.getValue(position)
                }
                .min()!!
    }

    companion object {
        fun from(input: List<String>): Circuit {
            require(input.size == 2)

            val dirMap = values().associateBy { it.symbol }

            val wirePositions = input.map { line ->
                val instructions = line.split(",")
                        .map { it.trim() }
                        .map {
                            dirMap.getValue(it.take(1)) to it.drop(1).toInt()
                        }

                val positions = arrayListOf(Vec2i.ZERO)

                instructions.forEach { (direction, amount) ->
                    val dirFunction: Vec2i.() -> Vec2i = when (direction) {
                        LEFT -> Vec2i::left
                        RIGHT -> Vec2i::right
                        UP -> Vec2i::up
                        DOWN -> Vec2i::down
                    }

                    repeat(amount) {
                        positions.add(positions.last().dirFunction())
                    }
                }

                positions
            }

            return Circuit(wirePositions[0], wirePositions[1])
        }
    }
}