package com.aoc2019.day17

object Runner {

    fun runPart1() {
        val computer = AsciiComputer.from(javaClass.getResource("/input_1.txt").readText())
        val state = computer.execute()

        println(state.alignmentParameters().sum())
    }

}

fun main() {
    Runner.runPart1()
}