package com.aoc2019.day16

class SignalDecoder(
        val initialPhase: Phase
) {

    val offset: Int = initialPhase.values
            .take(7)
            .joinToString("")
            .toInt()

    fun calculateMessage(basePattern: Pattern): String {
        var phase = initialPhase

        repeat(100) {
            phase = initialPhase.calculateNextPhase(basePattern)
        }

        return phase.values
                .drop(offset)
                .take(8)
                .joinToString("")
    }

}