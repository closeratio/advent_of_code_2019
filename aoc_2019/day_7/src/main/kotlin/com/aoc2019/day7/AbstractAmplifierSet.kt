package com.aoc2019.day7

abstract class AbstractAmplifierSet(
        val input: String
) {

    fun computeMaxOutputValue(): Pair<List<Long>, Long> {
        val elements = getAvailablePhaseSettings()

        val permutations = HashMap<List<Long>, Long>();
        while (permutations.size < 120) {
            val permutation = elements.toMutableList()
            permutation.shuffle()

            if (permutation !in permutations) {
                permutations[permutation] = computeOutputValue(permutation)
            }
        }

        return permutations.maxBy { it.value }!!.toPair()
    }

    abstract fun getAvailablePhaseSettings(): List<Long>

    abstract fun computeOutputValue(phaseSettings: List<Long>): Long

}