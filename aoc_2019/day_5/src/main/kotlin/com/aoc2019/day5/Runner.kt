package com.aoc2019.day5

import com.aoc2019.common.computer.Computer

object Runner {

    fun runPart1() {
        val computer = Computer.from(javaClass.getResource("/input.txt").readText(), listOf(1))

        computer.iterateUntilFinishedOrWaiting()

        println(computer.outputs)
    }

    fun runPart2() {
        val computer = Computer.from(javaClass.getResource("/input.txt").readText(), listOf(5))

        computer.iterateUntilFinishedOrWaiting()

        println(computer.outputs)
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}