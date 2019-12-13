package com.aoc2019.day2

import com.aoc2019.common.computer.Computer

object Runner {

    fun runPart1() {
        val program = Computer.from(javaClass.getResource("/input.txt").readText())

        program.program[1] = 12
        program.program[2] = 2

        program.iterateUntilFinished()
        println(program.program[0])
    }

    fun runPart2() {
        (0..99).forEach{ noun ->
            (0..99).forEach { verb ->
                val program = Computer.from(javaClass.getResource("/input.txt").readText())

                program.program[1] = noun
                program.program[2] = verb

                program.iterateUntilFinished()
                val output = program.program[0]
                if (output == 19690720) {
                    println(100 * noun + verb)
                    return
                }
            }
        }
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}