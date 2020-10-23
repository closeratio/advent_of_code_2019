package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i

data class PlayerState(
        val position: Vec2i,
        val heldKeys: Set<Key>,
        val stepsTaken: Long
): WorldObject() {

    fun adjacentStates(): Set<PlayerState> = hashSetOf(
            PlayerState(position.up(), heldKeys, stepsTaken + 1),
            PlayerState(position.right(), heldKeys, stepsTaken + 1),
            PlayerState(position.down(), heldKeys, stepsTaken + 1),
            PlayerState(position.left(), heldKeys, stepsTaken + 1)
    )

    fun getSubstate(): SubState = SubState(position, heldKeys)

    override fun toString(): String {
        return "Player(position=$position, stepsTaken=$stepsTaken, keys=(${heldKeys.joinToString(", ") { it.id }}))"
    }

    data class SubState(
            val position: Vec2i,
            val keys: Set<Key>
    )

}