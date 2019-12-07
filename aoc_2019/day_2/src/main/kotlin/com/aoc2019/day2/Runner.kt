package com.aoc2019.day2

object Runner {

    fun runPart1() {
        val program = Program.from(javaClass.getResource("/input.txt").readText())

        program.state[1] = 12
        program.state[2] = 2

        program.iterateUntilFinished()
        println(program.state[0])
    }

    fun runPart2() {
        (0..99).forEach{ noun ->
            (0..99).forEach { verb ->
                val program = Program.from(javaClass.getResource("/input.txt").readText())

                program.state[1] = noun
                program.state[2] = verb

                program.iterateUntilFinished()
                val output = program.state[0]
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