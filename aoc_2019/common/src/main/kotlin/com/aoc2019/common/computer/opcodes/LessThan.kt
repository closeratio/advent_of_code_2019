package com.aoc2019.common.computer.opcodes

import com.aoc2019.common.computer.Computer

class LessThan: Opcode() {

    override fun getCode(): Int = 7

    override fun execute(computer: Computer) {
        val modeIndicators = getModeIndicators(computer, 3)

        val paramValue1 = getParamValue(computer, modeIndicators[0], 1)
        val paramValue2 = getParamValue(computer, modeIndicators[1], 2)

        computer.memory[getWriteAddress(computer, modeIndicators[2], 3)] = if (paramValue1 < paramValue2) 1 else 0

        computer.programCounter += 4
    }
}