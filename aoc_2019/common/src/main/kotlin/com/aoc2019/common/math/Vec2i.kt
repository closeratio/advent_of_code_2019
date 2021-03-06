package com.aoc2019.common.math

class Vec2i(
        override val x: Int,
        override val y: Int
): Vec2<Int>() {

    fun left() = left(1)
    fun left(amount: Int) = this - Vec2i(amount, 0)

    fun west() = left()

    fun right() = right(1)
    fun right(amount: Int) = this + Vec2i(amount, 0)

    fun east() = right()

    fun up() = up(1)
    fun up(amount: Int) = this - Vec2i(0, amount)

    // I know this is a bit counter intuitive, but it's because most of the puzzles treat "down" as positive Y.
    fun south() = up()

    fun down() = down(1)
    fun down(amount: Int) = this + Vec2i(0, amount)

    fun north() = down()

    operator fun plus(other: Vec2i): Vec2i = Vec2i(x + other.x, y + other.y)
    operator fun minus(other: Vec2i): Vec2i = this + (other * -1)

    operator fun times(amount: Int): Vec2i = Vec2i(x * amount, y * amount)

    override fun angleTo(other: Vec2<*>): Double = Vec2d(this).angleTo(Vec2d(other))

    fun adjacentManhattanPositions() = listOf(
            north(),
            east(),
            south(),
            west()
    )

    companion object {
        val ZERO = from(0, 0)

        fun from(x: Number, y: Number) = Vec2i(x.toInt(), y.toInt())
    }
}