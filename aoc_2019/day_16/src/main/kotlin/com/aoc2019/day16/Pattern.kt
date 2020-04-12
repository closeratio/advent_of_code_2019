package com.aoc2019.day16

data class Pattern(
        val values: List<Int>
) {

    fun calculateOutputPattern(outputIndex: Int, length: Int): List<Int> {
        return 0.until(length)
                // The flatmap method is pretty wasteful because we only use the first length entries, but it can be
                // optimised later if needed
                .flatMap { phaseIndex ->
                    val patternIndex = phaseIndex % values.size
                    0.rangeTo(outputIndex).map {
                        values[patternIndex]
                    }
                }
                .let {
                    it.drop(1) + it.first() // Basically a left shift
                }
                .take(length)
    }

}