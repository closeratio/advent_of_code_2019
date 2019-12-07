package com.aoc2019.day2

class Program(
        val state: Array<Int>
) {

    var programCounter = 0
    var finished = false

    fun iterate() {
        if (finished) {
            return
        }

        val pc = programCounter
        when (state[pc]) {
            1 -> state[state[pc + 3]] = state[state[pc + 1]] + state[state[pc + 2]]
            2 -> state[state[pc + 3]] = state[state[pc + 1]] * state[state[pc + 2]]
            99 -> finished = true
            else -> throw IllegalStateException("Unknown opcode ${state[pc]} at index $pc")
        }

        if (!finished) {
            programCounter += 4
        }
    }

    fun iterateUntilFinished(): Program {
        while(!finished) {
            iterate()
        }
        return this
    }

    companion object {
        fun from(input: String): Program = Program(input
                .trim()
                .split(",")
                .map { it.trim().toInt() }
                .toTypedArray()
        )
    }

}