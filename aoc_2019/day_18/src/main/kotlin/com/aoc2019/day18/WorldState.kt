package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i
import java.util.*
import kotlin.Comparator
import kotlin.collections.HashSet

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
            availableKeyCache: AvailableKeyCache,
            maze: Maze
    ): Set<WorldState> {
        val cacheKey = playerState.getSubstate()

        // Check to see if we've computed the adjacent states already
        if (cacheKey in availableKeyCache.cache) {
            return availableKeyCache.cache
                    .getValue(cacheKey)
                    .map { obtainKey(it.first, playerState.stepsTaken + it.second) }
                    .toSet()
        }

        // Setup some data structures to keep track which keys we've found and which ones are still left to find as well
        // as the positions of the doors and keys.
        val foundKeys = HashSet<Pair<Key, Long>>()
        val remainingKeys = HashSet(keys)

        val doorPositions = doors.map { it.position }.toSet()
        val keyMap = keys.associateBy { it.position }

        // Open states are ones we've not yet evaluated, and visited positions are kept track of to make sure we don't
        // try the same position twice
        val openStates = PriorityQueue<PlayerState>(Comparator.comparingLong { it.stepsTaken })
        openStates.add(playerState)
        val visitedPositions = HashSet<Vec2i>()

        // Keep iterating until we've exausted all the positions (that we can reach) or all the keys have been found
        while (openStates.isNotEmpty() && remainingKeys.isNotEmpty()) {
            val state = openStates.remove()
            // Don't bother going any further if we've already been here
            if (state.position in visitedPositions) {
                continue
            }

            visitedPositions.add(state.position)

            // If we're currently "on a door", then we can't go any further
            // If we're "on a hey", then awesome, we've found a key, add it to the list (and don't go any further)
            // Otherwise, we must just be in a "normal space" and should add the adjacent spaces to the list of open
            // states
            if (state.position in doorPositions) {
                continue
            } else if (state.position in keyMap) {
                val key = keyMap.getValue(state.position)
                val delta = state.stepsTaken - playerState.stepsTaken
                remainingKeys.remove(key)
                foundKeys.add(key to delta)
            } else {
                openStates.addAll(maze.adjacentSpaces
                        .getValue(state.position)
                        .map { PlayerState(it, state.heldKeys, state.stepsTaken + 1) })
            }
        }

        availableKeyCache.cache[cacheKey] = foundKeys

        return foundKeys
                .map { obtainKey(it.first, playerState.stepsTaken + it.second) }
                .toSet()
    }

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