package com.aoc2019.common.computer.opcodes

import com.aoc2019.common.computer.Computer

class Halt: Opcode() {

    override fun getCode(): Int = 99

    override fun execute(computer: Computer) {
        computer.finished = true
    }
}