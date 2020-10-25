package com.aoc2019.day18

import com.aoc2019.day18.PlayerState.SubState
import java.util.*
import kotlin.Long.Companion.MAX_VALUE

class Planner(
        val initialState: WorldState,
        val maze: Maze
) {

    private val stateTransitionCache = AvailableKeyCache()
    private val bestSubStateMap = HashMap<SubState, Long>()
    private var bestMinimumSteps: Long = MAX_VALUE

    fun calculateSteps(): Long {
        val solutions = initialState
                .getAdjacentStates(stateTransitionCache, maze)
                .mapNotNull { evaluateState(it) };

        return solutions.minOrNull()!!
    }

    private fun evaluateState(worldState: WorldState): Long? {
        val substate = worldState.playerState.getSubstate()
        if (worldState.playerState.stepsTaken >= bestSubStateMap.getOrDefault(substate, MAX_VALUE)) {
            return null
        }

        bestSubStateMap[worldState.playerState.getSubstate()] = worldState.playerState.stepsTaken

        if (worldState.playerState.stepsTaken >= bestMinimumSteps) {
            return null
        }

        if (worldState.isCompleted()) {
            bestMinimumSteps = worldState.playerState.stepsTaken
            println(bestMinimumSteps)
            return worldState.playerState.stepsTaken
        }

        return worldState
                .getAdjacentStates(stateTransitionCache, maze)
                .sortedBy { it.playerState.stepsTaken }
                .mapNotNull { evaluateState(it) }
                .minOrNull()
    }

}