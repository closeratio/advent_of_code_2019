package com.aoc2019.common.math

import kotlin.math.atan2

class Vec2d(
        x: Number,
        y: Number
): Vec2<Double>() {

    override val x: Double = x.toDouble()
    override val y: Double = y.toDouble()

    constructor(vec: Vec2<*>): this(vec.x, vec.y)

    fun left() = left(1)
    fun left(amount: Number) = this - Vec2d(amount.toDouble(), 0.0)

    fun right() = right(1)
    fun right(amount: Number) = this + Vec2d(amount.toDouble(), 0.0)

    fun up() = up(1)
    fun up(amount: Number) = this - Vec2d(0.0, amount.toDouble())

    fun down() = down(1)
    fun down(amount: Number) = this + Vec2d(0.0, amount.toDouble())

    operator fun plus(other: Vec2d): Vec2d = Vec2d(x + other.x, y + other.y)
    operator fun minus(other: Vec2d): Vec2d = this + (other * -1)

    operator fun times(amount: Int): Vec2d = Vec2d(x * amount, y * amount)

    override fun angleTo(other: Vec2<*>): Double {
        val normalised = (Vec2d(this) - Vec2d(other)).normalize()
        val absoluteAngle = atan2(normalised.y, normalised.x)
        return absoluteAngle
    }

    private fun normalize(): Vec2d {
        val length = length()
        return Vec2d(x / length, y / length)
    }

    companion object {
        val ZERO = from(0, 0)

        fun from(x: Number, y: Number) = Vec2d(x.toDouble(), y.toDouble())
    }
}