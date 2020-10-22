package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i

class Player(
        val position: Vec2i,
        val heldKeys: Set<Key>,
        val stepsTaken: Long
): WorldObject() {

    fun withPosition(newPosition: Vec2i): Player = Player(
            newPosition,
            heldKeys,
            stepsTaken
    )

    fun withKey(key: Key): Player = Player(
            position,
            heldKeys + key,
            stepsTaken
    )

    fun withStepsTaken(stepsTaken: Long): Player = Player(
            position, heldKeys, stepsTaken
    )

    fun adjacentStates(): Set<Player> = hashSetOf(
            Player(position.up(), heldKeys, stepsTaken + 1),
            Player(position.right(), heldKeys, stepsTaken + 1),
            Player(position.down(), heldKeys, stepsTaken + 1),
            Player(position.left(), heldKeys, stepsTaken + 1)
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (position != other.position) return false
        if (heldKeys != other.heldKeys) return false
        if (stepsTaken != other.stepsTaken) return false

        return true
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + heldKeys.hashCode()
        result = 31 * result + stepsTaken.hashCode()
        return result
    }

    override fun toString(): String {
        return "Player(position=$position, stepsTaken=$stepsTaken)"
    }

}