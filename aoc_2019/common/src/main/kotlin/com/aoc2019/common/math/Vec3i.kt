package com.aoc2019.common.math

class Vec3i(
        override val x: Int,
        override val y: Int,
        override val z: Int
): Vec3<Int>() {

    operator fun plus(other: Vec3i): Vec3i = Vec3i(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vec3i): Vec3i = this + (other * -1)

    operator fun times(amount: Int): Vec3i = Vec3i(x * amount, y * amount, z * amount)

    companion object {
        val ZERO = from(0, 0, 0)

        fun from(x: Number, y: Number, z: Number) = Vec3i(x.toInt(), y.toInt(), z.toInt())
    }
}