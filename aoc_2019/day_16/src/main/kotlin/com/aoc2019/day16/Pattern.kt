package com.aoc2019.day16

data class Pattern(
        val values: List<Int>
) {

    fun calculateOutputPattern(outputIndex: Int, length: Int): List<Int> {
        val key = CacheKey(this, outputIndex, length)

        // Sequence is used to make it a lot faster
        return cache.getOrPut(key) {
            (0 until length)
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

    private data class CacheKey(
            val pattern: Pattern,
            val outputIndex: Int,
            val length: Int
    )

    companion object {
        private val cache = hashMapOf<CacheKey, List<Int>>()
    }

}