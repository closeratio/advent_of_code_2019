package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i

class Wall(
        val position: Vec2i
): WorldObject() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Wall) return false

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "Wall(position=$position)"
    }

}