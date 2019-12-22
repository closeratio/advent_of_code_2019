package com.aoc2019.common.computer.opcodes

import com.aoc2019.common.computer.Computer

class AdjustRelativeBase: Opcode() {

    override fun getCode(): Int = 9

    override fun execute(computer: Computer) {
        val modeIndicators = getModeIndicators(computer, 1)

        val paramValue1 = getParamValue(computer, modeIndicators[0], 1)

        computer.relativeBase += paramValue1

        computer.programCounter += 2
    }
}