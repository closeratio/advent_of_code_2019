package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i
import com.aoc2019.day18.StateTransitionCache.StateTransitionKey
import java.util.*
import kotlin.Comparator

data class WorldState(
        val playerState: PlayerState,
        val maze: Maze,
        val keys: Set<Key>,
        val doors: Set<Door>
) {

    fun obtainKey(key: Key, newSteps: Long): WorldState = WorldState(
            PlayerState(
                    key.position,
                    playerState.heldKeys + key,
                    newSteps
            ),
            maze,
            keys.filter { it != key }.toSet(),
            doors.filter { !it.worksWith(key) }.toSet()
    )

    fun isCompleted(): Boolean = keys.isEmpty()

    fun getAdjacentStates(stateTransitionCache: StateTransitionCache): Set<WorldState> = keys
            .mapNotNull { stateTransitionExists(it, stateTransitionCache) }
            .toSet()

    /**
     * Returns -1 if a route cannot be found
     */
    private fun stateTransitionExists(
            targetKey: Key,
            stateTransitionCache: StateTransitionCache
    ): WorldState? {
        val key = StateTransitionKey(playerState, targetKey)
        if (key in stateTransitionCache.cache) {
            return stateTransitionCache.cache.getValue(key).second
        }

        val openStates = PriorityQueue<PlayerState>(Comparator.comparingLong { it.stepsTaken })
        val visitedPositions = hashSetOf(playerState.position)

        openStates.addAll(filterAdjacentPlayerStates(
                playerState.adjacentStates(),
                targetKey,
                visitedPositions
        ))

        while (openStates.isNotEmpty()) {
            val state = openStates.remove()

            if (state.position in visitedPositions) {
                continue;
            }
            visitedPositions.add(state.position)

            if (state.position == targetKey.position) {
                val result = obtainKey(targetKey, state.stepsTaken)
                stateTransitionCache.cache[key] = true to result
                return result
            }

            openStates.addAll(filterAdjacentPlayerStates(
                    state.adjacentStates(),
                    targetKey,
                    visitedPositions
            ))
        }

        stateTransitionCache.cache[key] = false to null

        return null
    }

    private fun filterAdjacentPlayerStates(
            states: Set<PlayerState>,
            targetKey: Key,
            visitedPositions: Set<Vec2i>
    ): Set<PlayerState> = states
            .filter { state ->
                when {
                    state.position in visitedPositions -> false
                    state.position in maze.walls -> false
                    doors.any { it.position == state.position } -> false
                    keys.filter { it != targetKey }.any { it.position == state.position } -> false
                    else -> true
                }
            }
            .toSet()

    companion object {

        fun parse(input: String): WorldState {
            val objects = input.trim()
                    .split("\n")
                    .mapIndexed { y, line ->
                        line.mapIndexedNotNull { x, character ->
                            val vec = Vec2i(x, y)
                            if (character.isLetter() && character.isUpperCase()) {
                                Door(vec, character.toString())
                            } else if (character.isLetter()) {
                                Key(vec, character.toString())
                            } else {
                                when (character) {
                                    '#' -> Wall(vec)
                                    '@' -> PlayerState(vec, emptySet(), 0)

                                    else -> null
                                }
                            }
                        }
                    }
                    .flatten()

            return WorldState(
                    objects.filterIsInstance<PlayerState>().first(),
                    Maze(objects.filterIsInstance<Wall>().associateBy { it.position }),
                    objects.filterIsInstance<Key>().toSet(),
                    objects.filterIsInstance<Door>().toSet()
            )
        }

    }



}