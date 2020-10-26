package com.aoc2019.day17

import com.aoc2019.common.math.Vec2i
import com.aoc2019.day17.Direction.LEFT
import com.aoc2019.day17.Direction.RIGHT

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

    fun findRoute(): List<MovementFunctionPart> {
        val route = arrayListOf<MovementFunctionPart>()

        val stateList = arrayListOf(robot)
        val end = Vec2i(0, scaffold.maxByOrNull { it.position.y }!!.position.y)

        while (stateList.last().position != end) {
            val state = stateList.last()
            val direction = if (state.turn(LEFT).move(1).position in scaffoldMap) LEFT else RIGHT

            val movedStates = arrayListOf(state.turn(direction))
            while (movedStates.last().position in scaffoldMap) {
                movedStates.add(movedStates.last().move(1))
            }

            route.add(MovementFunctionPart(direction, movedStates.size - 2))
            stateList.add(movedStates.dropLast(1).last())
        }

        return route
    }

}