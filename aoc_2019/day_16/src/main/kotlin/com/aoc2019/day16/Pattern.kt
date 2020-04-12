package com.aoc2019.day16

data class Pattern(
        val values: List<Int>
) {

    fun calculateOutputPattern(outputIndex: Int, length: Int): List<Int> {
        // Sequence is used to make it a lot faster
        return (0 until length)
                .asSequence()
                .map { phaseIndex -> phaseIndex % values.size }
                .flatMap { patternIndex ->
                    0.rangeTo(outputIndex).map {
                        values[patternIndex]
                    }.asSequence()
                }
                .take(length + 1)
                .toList()
                .let {
                    it.drop(1) + it.first()
                }
    }

}