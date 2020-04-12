package com.aoc2019.day16

import kotlin.math.absoluteValue

data class Phase(
        val index: Long,
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

}