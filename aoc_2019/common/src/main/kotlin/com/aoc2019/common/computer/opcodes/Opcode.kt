package com.aoc2019.common.computer.opcodes

import com.aoc2019.common.computer.Computer
import com.aoc2019.common.computer.ParameterMode

abstract class Opcode {

    abstract fun getCode(): Int

    abstract fun execute(computer: Computer)

    protected fun getModeIndicators(
            computer: Computer,
            expectedLength: Int
    ): List<ParameterMode> = computer.memory[computer.programCounter]
            .toString()
            .dropLast(2)
            .reversed()
            .padEnd(expectedLength, '0')
            .map { it.toString().toInt() }
            .map {
                when(it) {
                    0 -> ParameterMode.POSITION
                    1 -> ParameterMode.IMMEDIATE
                    2 -> ParameterMode.RELATIVE
                    else -> throw IllegalArgumentException("Unhandled mode: $it")
                }
            }
            .take(expectedLength)

    protected fun getOffset(
            computer: Computer,
            pcOffset: Int
    ): Long = computer.memory[computer.programCounter + pcOffset]

    protected  fun getParamValue(
            computer: Computer,
            parameterMode: ParameterMode,
            pcOffset: Int
    ): Long {
        val offset = getOffset(computer, pcOffset)

        return when (parameterMode) {
            ParameterMode.IMMEDIATE -> offset
            ParameterMode.POSITION -> computer.memory[offset]
            ParameterMode.RELATIVE -> computer.memory[offset + computer.relativeBase]
        }
    }

    protected  fun getWriteAddress(
            computer: Computer,
            parameterMode: ParameterMode,
            pcOffset: Int
    ): Long {
        val offset = getOffset(computer, pcOffset)

        return when (parameterMode) {
            ParameterMode.POSITION -> offset
            ParameterMode.RELATIVE -> offset + computer.relativeBase
            ParameterMode.IMMEDIATE -> throw IllegalStateException("Immediate not allowed for write address")
        }
    }

}


