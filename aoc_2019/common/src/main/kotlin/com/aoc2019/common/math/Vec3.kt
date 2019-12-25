package com.aoc2019.common.math

import kotlin.math.abs
import kotlin.math.pow

abstract class Vec3<T: Number> {

    abstract val x: T
    abstract val y: T
    abstract val z: T

    fun <O: Number> manhattan(other: Vec3<O>): Double = abs(x.toDouble() - other.x.toDouble()) +
            abs(y.toDouble() - other.y.toDouble()) + abs(z.toDouble() - other.z.toDouble())

    fun length2(): Double = x.toDouble().pow(2) + y.toDouble().pow(2) + z.toDouble().pow(2)
    fun length(): Double = length2().pow(0.5)

    override fun toString(): String {
        return "${javaClass.simpleName}(x=$x, y=$y, z=$z)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vec3<*>

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        return result
    }


}

