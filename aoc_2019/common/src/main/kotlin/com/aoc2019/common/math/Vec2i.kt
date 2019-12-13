package com.aoc2019.common.math

class Vec2i(
        override val x: Int,
        override val y: Int
): Vec2<Int>() {

    fun left() = left(1)
    fun left(amount: Int) = this - Vec2i(amount, 0)

    fun right() = right(1)
    fun right(amount: Int) = this + Vec2i(amount, 0)

    fun up() = up(1)
    fun up(amount: Int) = this - Vec2i(0, amount)

    fun down() = down(1)
    fun down(amount: Int) = this + Vec2i(0, amount)

    private operator fun plus(other: Vec2i): Vec2i = Vec2i(x + other.x, y + other.y)
    private operator fun minus(other: Vec2i): Vec2i = this + (other * -1)

    private operator fun times(amount: Int): Vec2i = Vec2i(x * amount, y * amount)

    companion object {
        val ZERO = from(0, 0)

        fun from(x: Number, y: Number) = Vec2i(x.toInt(), y.toInt())
    }
}