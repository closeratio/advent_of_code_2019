package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i

data class RobotState(
        val robots: Set<Robot>,
        val heldKeys: Set<Key>
): WorldObject() {

    val stepsTaken: Long = robots.map { it.stepsTaken }.sum()

    fun getSubstate(): SubState = SubState(robots.map { it.position }.toSet(), heldKeys)

    data class SubState(
            val positions: Set<Vec2i>,
            val keys: Set<Key>
    )

}

