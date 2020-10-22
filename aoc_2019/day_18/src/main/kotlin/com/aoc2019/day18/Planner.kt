package com.aoc2019.day18

import java.util.*
import kotlin.collections.HashSet

class Planner(
        val initialState: WorldState
) {

    fun calculateSteps(): Long {
        val openStates = PriorityQueue<WorldState>(Comparator.comparingLong { it.player.stepsTaken })
        val closedStates = HashSet<WorldState>()

        // Populate initial open states
        openStates.addAll(initialState.getAdjacentStates())

        while (!openStates.isEmpty()) {
            val state = openStates.remove()

            if (state in closedStates) {
                continue
            }
            closedStates.add(state)

            if (state.isCompleted()) {
                return state.player.stepsTaken
            }

            openStates.addAll(state.getAdjacentStates().filter { it !in closedStates })
        }

        throw IllegalStateException("Unable to find route")
    }

}