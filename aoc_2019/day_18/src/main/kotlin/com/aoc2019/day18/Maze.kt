package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i

data class Maze(
        val walls: Map<Vec2i, Wall>
) {

    val adjacentSpaces: Map<Vec2i, Set<Vec2i>> = walls
            .let {
                val min = walls.keys.sortedBy { it.x }.sortedBy { it.y }.first()
                val max = walls.keys.sortedByDescending { it.x }.sortedByDescending { it.y }.first()

                min.y.rangeTo(max.y).flatMap { y ->
                    min.x.rangeTo(max.x).map { x ->
                        Vec2i(x, y)
                    }
                }
            }
            .filter { it !in walls }
            .associateWith { pos ->
                pos.adjacentManhattanPositions()
                        .filter { it !in walls }
                        .toSet()
            }

}