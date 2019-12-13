package com.aoc2019.common.computer

class Computer(
        val program: Array<Int>,
        val input: Int?
) {

    var programCounter = 0
    var finished = false
    var output: Int? = null

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
            99 -> halt()
            else -> throw IllegalStateException("Unknown opcode ${program[pc]} at index $pc")
        }
    }

    fun add() {
        val pc = programCounter
        program[program[pc + 3]] = program[program[pc + 1]] + program[program[pc + 2]]

        programCounter += 4
    }

    fun multiply() {
        val pc = programCounter
        program[program[pc + 3]] = program[program[pc + 1]] * program[program[pc + 2]]

        programCounter += 4
    }

    fun takeInput() {
        // TODO: Implement
        programCounter += 2
    }

    fun writeOutput() {
        // TODO: IMplement
        programCounter += 2
    }

    fun halt() {
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
                input: Int? = null
        ): Computer = Computer(programData
                .trim()
                .split(",")
                .map { it.trim().toInt() }
                .toTypedArray(),
                input
        )
    }

}