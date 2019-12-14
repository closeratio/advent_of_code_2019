package com.aoc2019.common.computer

import com.aoc2019.common.computer.ParameterMode.IMMEDIATE
import com.aoc2019.common.computer.ParameterMode.POSITION

class Computer(
        val program: Array<Int>,
        val inputValues: ArrayList<Int>
) {

    var programCounter = 0
    var finished = false
    var outputs = ArrayList<Int>()

    fun iterate() {
        if (finished) {
            return
        }

        val pc = programCounter
        when (program[pc].toString().takeLast(2).toInt()) {
            1 -> add()
            2 -> multiply()
            3 -> takeInput()
            4 -> writeOutput()
            5 -> jumpIfTrue()
            6 -> jumpIfFalse()
            7 -> lessThan()
            8 -> equals()
            99 -> halt()
            else -> throw IllegalStateException("Unknown opcode ${program[pc]} at index $pc")
        }
    }

    private fun getModeIndicators(expectedLength: Int): List<ParameterMode> = program[programCounter]
            .toString()
            .dropLast(2)
            .reversed()
            .padEnd(expectedLength, '0')
            .map { it.toString().toInt() }
            .map {
                when(it) {
                    0 -> POSITION
                    1 -> IMMEDIATE
                    else -> throw IllegalArgumentException("Unhandled mode: $it")
                }
            }
            .take(expectedLength)

    private fun getParamValue(
            parameterMode: ParameterMode,
            pcOffset: Int
    ) = when (parameterMode) {
        POSITION -> program[program[programCounter + pcOffset]]
        IMMEDIATE -> program[programCounter + pcOffset]
    }

    private fun add() {
        val modeIndicators = getModeIndicators(2)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        program[program[programCounter + 3]] = paramValue1 + paramValue2

        programCounter += 4
    }

    private fun multiply() {
        val modeIndicators = getModeIndicators(2)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        program[program[programCounter + 3]] = paramValue1 * paramValue2

        programCounter += 4
    }

    private fun takeInput() {
        program[program[programCounter + 1]] = inputValues.first()

        inputValues.removeAt(0) // "Consume" the value

        programCounter += 2
    }

    private fun writeOutput() {

        val modeIndicators = getModeIndicators(1)

        val paramValue = getParamValue(modeIndicators[0], 1)

        outputs.add(paramValue)

        programCounter += 2
    }

    private fun jumpIfTrue() {
        val modeIndicators = getModeIndicators(2)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        if (paramValue1 != 0) {
            programCounter = paramValue2
        } else {
            programCounter += 3
        }
    }

    private fun jumpIfFalse() {
        val modeIndicators = getModeIndicators(2)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        if (paramValue1 == 0) {
            programCounter = paramValue2
        } else {
            programCounter += 3
        }
    }

    private fun lessThan() {
        val modeIndicators = getModeIndicators(2)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        program[program[programCounter + 3]] = if (paramValue1 < paramValue2) 1 else 0

        programCounter += 4
    }

    private fun equals() {
        val modeIndicators = getModeIndicators(2)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        program[program[programCounter + 3]] = if (paramValue1 == paramValue2) 1 else 0

        programCounter += 4
    }

    private fun halt() {
        finished = true
    }

    fun iterateUntilFinished(): Computer {
        while(!finished) {
            iterate()
        }
        return this
    }

    companion object {
        fun from(
                programData: String,
                input: List<Int> = listOf()
        ): Computer = Computer(programData
                .trim()
                .split(",")
                .map { it.trim().toInt() }
                .toTypedArray(),
                ArrayList(input)
        )
    }

}