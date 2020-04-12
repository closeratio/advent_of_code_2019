package com.aoc2019.day16

import kotlin.math.absoluteValue

data class Phase private constructor(
        val index: Int,
        val values: List<Int>
) {

    fun calculateNextPhase(basePattern: Pattern): Phase {
        val output: List<Int> = values.mapIndexed { outputIndex, _ ->
            val pattern = basePattern.calculateOutputPattern(outputIndex, values.size)

            values.mapIndexed { patternIndex, value -> pattern[patternIndex] * value}
                    .sum()
                    .rem(10)
                    .absoluteValue
        }

        return Phase(
                index + 1,
                output
        )
    }

    companion object {
        fun from(
                input: String,
                index: Int = 0,
                repeat: Int = 1
        ): Phase = Phase(
                index,
                input.map { it.toString().toInt() }
                        .let { values ->
                            (1..repeat).flatMap { values }
                        }
        )
    }

}