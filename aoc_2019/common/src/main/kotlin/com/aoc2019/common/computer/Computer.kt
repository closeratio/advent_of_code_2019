package com.aoc2019.common.computer

import com.aoc2019.common.computer.ParameterMode.*
import java.util.*
import kotlin.collections.LinkedHashMap

class Computer(
        val memory: Memory,
        val inputValues: LinkedList<Long>
) {

    var programCounter = 0L
    var relativeBase = 0L
    var finished = false
    var waiting = false
    var outputs = LinkedList<Long>()

    fun iterate() {
        if (finished) {
            return
        }

        val pc = programCounter
        when (val opcode = memory[pc].toString().takeLast(2).toInt()) {
            1 -> add()
            2 -> multiply()
            3 -> takeInput()
            4 -> writeOutput()
            5 -> jumpIfTrue()
            6 -> jumpIfFalse()
            7 -> lessThan()
            8 -> equals()
            9 -> adjustRelativeBase()
            99 -> halt()
            else -> throw IllegalStateException("Unknown opcode $opcode at index $pc")
        }
    }

    fun iterateUntilFinishedOrWaiting(): Computer {
        waiting = false
        while(!finished && !waiting) {
            iterate()
        }
        return this
    }

    private fun getModeIndicators(expectedLength: Int): List<ParameterMode> = memory[programCounter]
            .toString()
            .dropLast(2)
            .reversed()
            .padEnd(expectedLength, '0')
            .map { it.toString().toInt() }
            .map {
                when(it) {
                    0 -> POSITION
                    1 -> IMMEDIATE
                    2 -> RELATIVE
                    else -> throw IllegalArgumentException("Unhandled mode: $it")
                }
            }
            .take(expectedLength)

    private fun getOffset(pcOffset: Int): Long = memory[programCounter + pcOffset]

    private fun getParamValue(
            parameterMode: ParameterMode,
            pcOffset: Int
    ): Long {
        val offset = getOffset(pcOffset)

        return when (parameterMode) {
            IMMEDIATE -> offset
            POSITION -> memory[offset]
            RELATIVE -> memory[offset + relativeBase]
        }
    }

    private fun getWriteAddress(
            parameterMode: ParameterMode,
            pcOffset: Int
    ): Long {
        val offset = getOffset(pcOffset)

        return when (parameterMode) {
            POSITION -> offset
            RELATIVE -> offset + relativeBase
            IMMEDIATE -> throw IllegalStateException("Immediate not allowed for write address")
        }
    }

    private fun add() {
        val modeIndicators = getModeIndicators(3)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        memory[getWriteAddress(modeIndicators[2], 3)] = paramValue1 + paramValue2

        programCounter += 4
    }

    private fun multiply() {
        val modeIndicators = getModeIndicators(3)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        memory[getWriteAddress(modeIndicators[2], 3)] = paramValue1 * paramValue2

        programCounter += 4
    }

    private fun takeInput() {
        if (inputValues.isEmpty()) {
            waiting = true
            return
        }

        val modeIndicators = getModeIndicators(1)

        memory[getWriteAddress(modeIndicators[0], 1)] = inputValues.pop()

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

        if (paramValue1 != 0L) {
            programCounter = paramValue2
        } else {
            programCounter += 3
        }
    }

    private fun jumpIfFalse() {
        val modeIndicators = getModeIndicators(2)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        if (paramValue1 == 0L) {
            programCounter = paramValue2
        } else {
            programCounter += 3
        }
    }

    private fun lessThan() {
        val modeIndicators = getModeIndicators(3)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        memory[getWriteAddress(modeIndicators[2], 3)] = if (paramValue1 < paramValue2) 1 else 0

        programCounter += 4
    }

    private fun equals() {
        val modeIndicators = getModeIndicators(3)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        memory[getWriteAddress(modeIndicators[2], 3)] = if (paramValue1 == paramValue2) 1 else 0

        programCounter += 4
    }

    private fun adjustRelativeBase() {
        val modeIndicators = getModeIndicators(1)

        val paramValue1 = getParamValue(modeIndicators[0], 1)

        relativeBase += paramValue1

        programCounter += 2
    }

    private fun halt() {
        finished = true
    }

    companion object {
        fun from(
                programData: String,
                input: List<Long> = listOf()
        ): Computer = Computer(Memory(LinkedHashMap(programData
                .trim()
                .split(",")
                .mapIndexed { index, value -> index.toLong() to value.trim().toLong() }
                .toMap())),
                LinkedList(input)
        )
    }

}