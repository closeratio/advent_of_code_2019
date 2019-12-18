package com.aoc2019.day7

import com.aoc2019.common.computer.Computer

class FeedbackAmplifierSet(
        input: String
): AbstractAmplifierSet(input) {

    override fun getAvailablePhaseSettings(): List<Int> = listOf(5, 6, 7, 8, 9)

    override fun computeOutputValue(phaseSettings: List<Int>): Int {

        val computers = phaseSettings.map {
            Computer.from(input, listOf(it))
        }

        computers.first().inputValues.add(0)

        while (!computers.last().finished) {
            computers.forEachIndexed { index, computer ->

                val prevOutputs = computers[if (index == 0) computers.size - 1 else index - 1].outputs

                if (!computer.waiting) {
                    computer.iterateUntilFinishedOrWaiting()
                } else if (prevOutputs.isNotEmpty()) {
                    computer.inputValues.add(prevOutputs.pop())

                    computer.iterateUntilFinishedOrWaiting()
                }
            }
        }

        return computers.last().outputs.first()
    }

}