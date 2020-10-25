package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i
import java.util.*
import kotlin.Comparator
import kotlin.collections.HashSet

data class WorldState(
        val robotState: RobotState,
        val keys: Set<Key>,
        val doors: Set<Door>
) {

    fun obtainKey(
            robot: Robot,
            key: Key,
            newSteps: Long
    ): WorldState = WorldState(
            RobotState(
                    robotState.robots.filter { it != robot }.toSet() + Robot(key.position, newSteps),
                    robotState.heldKeys + key
            ),
            keys.filter { it != key }.toSet(),
            doors.filter { !it.worksWith(key) }.toSet()
    )

    fun isCompleted(): Boolean = keys.isEmpty()

    fun getAdjacentStates(
            maze: Maze
    ): Set<WorldState> = robotState.robots
            .flatMap { getAdjacentStates(it, maze) }
            .toSet()

    private fun getAdjacentStates(
            robot: Robot,
            maze: Maze
    ): Set<WorldState> {

        // Setup some data structures to keep track which keys we've found and which ones are still left to find as well
        // as the positions of the doors and keys.
        val foundKeys = HashSet<Pair<Key, Long>>()
        val remainingKeys = HashSet(keys)

        val doorPositions = doors.map { it.position }.toSet()
        val keyMap = keys.associateBy { it.position }

        // Open states are ones we've not yet evaluated, and visited positions are kept track of to make sure we don't
        // try the same position twice
        val openStates = PriorityQueue<Robot>(Comparator.comparingLong { it.stepsTaken })
        openStates.add(robot)
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
            // If we're "on a key", then awesome, we've found a key, add it to the list (and don't go any further)
            // Otherwise, we must just be in a "normal space" and should add the adjacent spaces to the list of open
            // states
            if (state.position in doorPositions) {
                continue
            } else if (state.position in keyMap) {
                val key = keyMap.getValue(state.position)
                val delta = state.stepsTaken - robotState.stepsTaken
                remainingKeys.remove(key)
                foundKeys.add(key to delta)
            } else {
                openStates.addAll(maze.adjacentSpaces
                        .getValue(state.position)
                        .map { Robot(it, state.stepsTaken + 1) })
            }
        }

        return foundKeys
                .map { obtainKey(robot, it.first, robotState.stepsTaken + it.second) }
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
                                    '@' -> Robot(vec, 0)
                                    else -> null
                                }
                            }
                        }
                    }
                    .flatten()

            return WorldState(
                    RobotState(objects.filterIsInstance<Robot>().toSet(), setOf()),
                    objects.filterIsInstance<Key>().toSet(),
                    objects.filterIsInstance<Door>().toSet()
            ) to Maze(
                    objects.filterIsInstance<Wall>().associateBy { it.position }
            )
        }

    }


}