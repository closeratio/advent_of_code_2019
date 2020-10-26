package com.aoc2019.day17

object Runner {

    fun runPart1() {
        val computer = AsciiComputer.from(javaClass.getResource("/input_1.txt").readText())
        val state = computer.getScaffoldMap()

        println(state.alignmentParameters().sum())
    }

    fun runPart2() {
        val computer = AsciiComputer.from(javaClass.getResource("/input_1.txt").readText())

        println(computer.traverseScaffold())
    }

}

fun main() {
    Runner.runPart2()
}