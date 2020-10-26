package com.aoc2019.day17

class WorldState(
        val scaffold: Set<Scaffold>,
        val robot: Robot
) {

    private val scaffoldMap = scaffold.associateBy { it.position }

    fun alignmentParameters(): List<Int> = scaffold
            .filter { s ->
                s.position.adjacentManhattanPositions().all { it in scaffoldMap }
            }
            .map { it.position }
            .map { it.x * it.y }

}