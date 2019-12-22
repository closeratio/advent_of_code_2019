package com.aoc2019.common.computer.opcodes

import com.aoc2019.common.computer.Computer

class JumpIfFalse: Opcode() {

    override fun getCode(): Int = 6

    override fun execute(computer: Computer) {
        val modeIndicators = getModeIndicators(computer, 2)

        val paramValue1 = getParamValue(computer, modeIndicators[0], 1)
        val paramValue2 = getParamValue(computer, modeIndicators[1], 2)

        if (paramValue1 == 0L) {
            computer.programCounter = paramValue2
        } else {
            computer.programCounter += 3
        }
    }
}