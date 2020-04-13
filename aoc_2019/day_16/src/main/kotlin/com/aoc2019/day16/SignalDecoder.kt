package com.aoc2019.day16

import kotlin.math.absoluteValue

class SignalDecoder(
        val initialPhase: Phase
) {

    val offset: Int = initialPhase.values
            .take(7)
            .joinToString("")
            .toInt()

    fun calculateMessage(): String {
        val values = initialPhase.values.drop(offset).toTypedArray()

        repeat(100) {
            values.indices.reversed().fold(0) { acc, index ->
                (acc + values[index]).also { values[index] = (it % 10).absoluteValue }
            }
        }

        return values.take(8).joinToString("")
    }

}