package com.aoc2019.day18

import com.aoc2019.day18.PlayerState.SubState
import java.util.*
import kotlin.collections.LinkedHashSet

class Planner(
        val initialState: WorldState
) {

    private val stateTransitionCache = StateTransitionCache()
    private val bestSubstateMap = HashMap<SubState, Long>()

    fun calculateSteps(): Long {
        val openStates = PriorityQueue<WorldState>(Comparator.comparingLong { it.playerState.stepsTaken })
        val closedStates = LinkedHashSet<WorldState>()

        // Populate initial open states
        openStates.addAll(initialState.getAdjacentStates(stateTransitionCache))

        while (!openStates.isEmpty()) {
            val state = openStates.remove()

            if (state in closedStates) {
                continue
            }

            if (state.isCompleted()) {
                return state.playerState.stepsTaken
            }

            bestSubstateMap[state.playerState.getSubstate()] = state.playerState.stepsTaken
            closedStates.add(state)

            val adjacent = state.getAdjacentStates(stateTransitionCache)
            openStates.addAll(adjacent
                    .filter { it !in closedStates }
                    .filter { it.playerState.getSubstate() !in bestSubstateMap }
            )
        }

        throw IllegalStateException("Unable to find route")
    }

}