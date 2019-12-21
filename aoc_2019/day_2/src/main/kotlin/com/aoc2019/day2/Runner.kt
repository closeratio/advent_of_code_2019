package com.aoc2019.day2

import com.aoc2019.common.computer.Computer

object Runner {

    fun runPart1() {
        val program = Computer.from(javaClass.getResource("/input.txt").readText())

        program.memory[1] = 12
        program.memory[2] = 2

        program.iterateUntilFinishedOrWaiting()
        println(program.memory[0])
    }

    fun runPart2() {
        (0..99).forEach{ noun ->
            (0..99).forEach { verb ->
                val program = Computer.from(javaClass.getResource("/input.txt").readText())

                program.memory[1] = noun.toLong()
                program.memory[2] = verb.toLong()

                program.iterateUntilFinishedOrWaiting()
                val output = program.memory[0]
                if (output == 19690720L) {
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