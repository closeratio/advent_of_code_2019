package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i
import com.aoc2019.day18.StateTransitionCache.StateTransitionKey
import java.util.*
import kotlin.Comparator

data class WorldState(
        val playerState: PlayerState,
        val keys: Set<Key>,
        val doors: Set<Door>
) {

    fun obtainKey(key: Key, newSteps: Long): WorldState = WorldState(
            PlayerState(
                    key.position,
                    playerState.heldKeys + key,
                    newSteps
            ),
            keys.filter { it != key }.toSet(),
            doors.filter { !it.worksWith(key) }.toSet()
    )

    fun isCompleted(): Boolean = keys.isEmpty()

    fun getAdjacentStates(
            stateTransitionCache: StateTransitionCache,
            maze: Maze
    ): Set<WorldState> = keys
            .mapNotNull { stateTransitionExists(it, stateTransitionCache, maze) }
            .toSet()

    /**
     * Returns -1 if a route cannot be found
     */
    private fun stateTransitionExists(
            targetKey: Key,
            stateTransitionCache: StateTransitionCache,
            maze: Maze
    ): WorldState? {
        val key = StateTransitionKey(playerState, targetKey)
        if (key in stateTransitionCache.cache) {
            return stateTransitionCache.cache.getValue(key).second
        }

        val openStates = PriorityQueue<PlayerState>(Comparator.comparingLong { it.stepsTaken })
        val visitedPositions = HashSet<Vec2i>()

        openStates.add(playerState)

        while (openStates.isNotEmpty()) {
            val state = openStates.remove()

            if (state.position in visitedPositions) {
                continue
            }
            visitedPositions.add(state.position)

            if (state.position == targetKey.position) {
                val result = obtainKey(targetKey, state.stepsTaken)
                stateTransitionCache.cache[key] = true to result
                return result
            }

            openStates.addAll(filterAdjacentPlayerStates(
                    maze.adjacentSpaces
                            .getValue(state.position)
                            .map {
                                PlayerState(it, state.heldKeys, state.stepsTaken + 1)
                            }
                            .toSet(),
                    targetKey,
                    visitedPositions,
                    maze
            ))
        }

        stateTransitionCache.cache[key] = false to null

        return null
    }

    private fun filterAdjacentPlayerStates(
            states: Set<PlayerState>,
            targetKey: Key,
            visitedPositions: Set<Vec2i>,
            maze: Maze
    ): Set<PlayerState> = states
            .filter { state ->
                when {
//                    state.position in maze.walls -> false
                    state.position in visitedPositions -> false
                    doors.any { it.position == state.position } -> false
                    keys.filter { it != targetKey }.any { it.position == state.position } -> false
                    else -> true
                }
            }
            .toSet()

    companion object {

        fun parse(input: String): Pair<WorldState, Maze> {
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
                    objects.filterIsInstance<Key>().toSet(),
                    objects.filterIsInstance<Door>().toSet()
            ) to Maze(
                    objects.filterIsInstance<Wall>().associateBy { it.position }
            )
        }

    }


}