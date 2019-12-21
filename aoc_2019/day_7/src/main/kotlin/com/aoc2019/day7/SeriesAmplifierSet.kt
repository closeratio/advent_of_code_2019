package com.aoc2019.day7

import com.aoc2019.common.computer.Computer

class SeriesAmplifierSet(
        input: String
): AbstractAmplifierSet(input) {

    override fun getAvailablePhaseSettings(): List<Long> = listOf(0, 1, 2, 3, 4)

    override fun computeOutputValue(phaseSettings: List<Long>): Long {

        val amplifiers = phaseSettings.map {
            Computer.from(input, listOf(it))
        }

        amplifiers.forEachIndexed { index, amplifier ->
            // Add output from previous
            amplifier.inputValues.add(when (index) {
                0 -> 0
                else -> amplifiers[index - 1].outputs.first()
            })

            amplifier.iterateUntilFinishedOrWaiting()
        }

        return amplifiers.last().outputs.first()
    }

}