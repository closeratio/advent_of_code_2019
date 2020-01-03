package com.aoc2019.common.math

import java.math.BigInteger

fun Double.toDegrees(): Double = Math.toDegrees(this)
fun Double.toRadians(): Double = Math.toRadians(this)

fun <T: Number> Collection<T>.lowestCommonMultiplier(): Long {
    require(isNotEmpty())

    if (size == 1) {
        return first().toLong()
    }

    return map { BigInteger.valueOf(it.toLong()) }
            .reduce { acc, curr ->
                val gcd = acc.gcd(curr)
                val absProd = acc.multiply(curr).abs()
                absProd.divide(gcd)
            }.let {
                it.toLong()
            }
}