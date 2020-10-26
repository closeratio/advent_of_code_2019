package com.aoc2019.day17

import com.aoc2019.common.math.Vec2i
import com.aoc2019.day17.Direction.LEFT
import com.aoc2019.day17.Direction.RIGHT
import com.aoc2019.day17.Orientation.*

data class Robot(
        val position: Vec2i,
        val orientation: Orientation
) {

    fun turn(direction: Direction): Robot = Robot(
            position,
            when (direction) {
                LEFT -> orientation.left()
                RIGHT -> orientation.right()
            }
    )

    fun move(amount: Int): Robot = Robot(
            when (orientation) {
                NORTH -> position.up(amount)
                EAST -> position.right(amount)
                SOUTH -> position.down(amount)
                WEST -> position.left(amount)
            },
            orientation
    )

}

