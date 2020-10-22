package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i
import java.util.*

class WorldState(
        val player: Player,
        val maze: Maze,
        val keys: Set<Key>,
        val doors: Set<Door>
) {

    fun movePlayer(
            newPosition: Vec2i,
            newSteps: Long
    ): WorldState = WorldState(
            player.withPosition(newPosition).withStepsTaken(newSteps),
            maze,
            keys,
            doors
    )

    fun obtainKey(key: Key): WorldState = WorldState(
            player.withKey(key),
            maze,
            keys.filter { it != key }.toSet(),
            doors.filter { !it.worksWith(key) }.toSet()
    )

    fun isCompleted(): Boolean = keys.isEmpty()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WorldState) return false

        if (player != other.player) return false
        if (maze != other.maze) return false
        if (keys != other.keys) return false
        if (doors != other.doors) return false

        return true
    }

    override fun hashCode(): Int {
        var result = player.hashCode()
        result = 31 * result + maze.hashCode()
        result = 31 * result + keys.hashCode()
        result = 31 * result + doors.hashCode()
        return result
    }

    fun getAdjacentStates(): Set<WorldState> {
        return keys.mapNotNull { stateTransitionExists(it) }.toSet()
    }

    /**
     * Returns -1 if a route cannot be found
     */
    fun stateTransitionExists(targetKey: Key): WorldState? {
        val openStates = PriorityQueue<Player>(Comparator.comparingLong { it.stepsTaken })
        val visitedPositions = hashSetOf(player.position)

        openStates.addAll(filterAdjacentPlayerStates(
                player.adjacentStates(),
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
                return movePlayer(state.position, state.stepsTaken)
                        .obtainKey(targetKey)
            }

            openStates.addAll(filterAdjacentPlayerStates(
                    state.adjacentStates(),
                    targetKey,
                    visitedPositions
            ))
        }

        return null
    }

    fun filterAdjacentPlayerStates(
            states: Set<Player>,
            targetKey: Key,
            visitedPositions: Set<Vec2i>
    ): Set<Player> = states
            .asSequence()
            .filter { it.position !in visitedPositions }
            .filter { it.position !in maze.wallMap }
            .filter { state -> !doors.any { it.position == state.position } }
            .filter { state -> !keys.filter { it != targetKey }.any { it.position == state.position } }
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
                                    '@' -> Player(vec, emptySet(), 0)

                                    else -> null
                                }
                            }
                        }
                    }
                    .flatten()

            return WorldState(
                    objects.filterIsInstance<Player>().first(),
                    Maze(objects.filterIsInstance<Wall>().toSet()),
                    objects.filterIsInstance<Key>().toSet(),
                    objects.filterIsInstance<Door>().toSet()
            )
        }

    }

}