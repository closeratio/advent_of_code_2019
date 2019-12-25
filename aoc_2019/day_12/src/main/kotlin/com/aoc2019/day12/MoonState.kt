package com.aoc2019.day12

import com.aoc2019.common.math.Vec3i

class MoonState(
        val name: String,
        val position: Vec3i,
        val velocity: Vec3i
) {

    private fun calculateComponentDelta(currComponent: Int, otherComponent: Int): Int = when {
        currComponent > otherComponent -> -1
        currComponent < otherComponent -> 1
        else -> 0
    }

    fun calculateVelocityDelta(otherMoon: MoonState): Vec3i = Vec3i(
            calculateComponentDelta(position.x, otherMoon.position.x),
            calculateComponentDelta(position.y, otherMoon.position.y),
            calculateComponentDelta(position.z, otherMoon.position.z)
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MoonState

        if (name != other.name) return false
        if (position != other.position) return false
        if (velocity != other.velocity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + position.hashCode()
        result = 31 * result + velocity.hashCode()
        return result
    }

    override fun toString(): String {
        return "MoonState(name='$name', position=$position, velocity=$velocity)"
    }

}