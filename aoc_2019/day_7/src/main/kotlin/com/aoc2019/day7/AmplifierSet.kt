package com.aoc2019.day7

import com.aoc2019.common.computer.Computer

class AmplifierSet(
        val input: String
) {

    fun computeMaxOutputValue(): Pair<List<Int>, Int> {
        val elements = listOf(0, 1, 2, 3, 4)

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

    fun computeOutputValue(phaseSettings: List<Int>): Int {

        val amplifiers = phaseSettings.map {
            Amplifier(
                    Computer.from(input, listOf(it))
            )
        }

        amplifiers.forEachIndexed { index, amplifier ->
            // Add output from previous
            amplifier.computer.inputValues.add(when (index) {
                0 -> 0
                else -> amplifiers[index - 1].computer.outputs.first()
            })

            amplifier.computeOutput()
        }

        return amplifiers.last().computer.outputs.first()
    }

}