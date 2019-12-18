package com.aoc2019.day7

abstract class AbstractAmplifierSet(
        val input: String
) {

    fun computeMaxOutputValue(): Pair<List<Int>, Int> {
        val elements = getAvailablePhaseSettings()

        val permutations = HashMap<List<Int>, Int>();
        while (permutations.size < 120) {
            val permutation = elements.toMutableList()
            permutation.shuffle()

            if (permutation !in permutations) {
                permutations[permutation] = computeOutputValue(permutation)
            }
        }

        return permutations.maxBy { it.value }!!.toPair()
    }

    abstract fun getAvailablePhaseSettings(): List<Int>

    abstract fun computeOutputValue(phaseSettings: List<Int>): Int

}