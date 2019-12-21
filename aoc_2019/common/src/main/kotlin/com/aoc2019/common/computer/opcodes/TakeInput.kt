package com.aoc2019.common.computer.opcodes

import com.aoc2019.common.computer.Computer

class TakeInput: Opcode() {

    override fun getCode(): Int = 3

    override fun execute(computer: Computer) {
        if (computer.inputValues.isEmpty()) {
            computer.waiting = true
            return
        }

        val modeIndicators = getModeIndicators(computer, 1)

        computer.memory[getWriteAddress(computer, modeIndicators[0], 1)] = computer.inputValues.pop()

        computer.programCounter += 2
    }
}