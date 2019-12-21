package com.aoc2019.common.computer.opcodes

import com.aoc2019.common.computer.Computer

class WriteOutput: Opcode() {

    override fun getCode(): Int = 4

    override fun execute(computer: Computer) {
        val modeIndicators = getModeIndicators(computer, 1)

        val paramValue = getParamValue(computer, modeIndicators[0], 1)

        computer.outputs.add(paramValue)

        computer.programCounter += 2
    }
}