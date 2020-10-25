package com.aoc2019.day18

import com.aoc2019.day18.RobotState.SubState
import java.util.*
import kotlin.Long.Companion.MAX_VALUE

class Planner(
        val initialState: WorldState,
        val maze: Maze
) {

    private val bestSubStateMap = HashMap<SubState, Long>()
    private var bestMinimumSteps: Long = MAX_VALUE

    fun calculateSteps(): Long {
        val solutions = initialState
                .getAdjacentStates(maze)
                .mapNotNull { evaluateState(it) };

        return solutions.minOrNull()!!
    }

    private fun evaluateState(worldState: WorldState): Long? {
        val substate = worldState.robotState.getSubstate()
        val stepsTaken = worldState.robotState.stepsTaken
        if (stepsTaken >= bestSubStateMap.getOrDefault(substate, MAX_VALUE)) {
            return null
        }

        bestSubStateMap[worldState.robotState.getSubstate()] = stepsTaken

        if (stepsTaken >= bestMinimumSteps) {
            return null
        }

        if (worldState.isCompleted()) {
            bestMinimumSteps = stepsTaken
            println(bestMinimumSteps)
            return stepsTaken
        }

        return worldState
                .getAdjacentStates(maze)
                .sortedBy { it.robotState.stepsTaken }
                .mapNotNull { evaluateState(it) }
                .minOrNull()
    }

}