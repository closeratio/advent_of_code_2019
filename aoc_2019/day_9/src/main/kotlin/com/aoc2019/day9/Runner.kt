package com.aoc2019.day9

import com.aoc2019.common.computer.Computer

object Runner {

    fun runPart1() {
        val computer = Computer.from(javaClass.getResource("/input.txt").readText(), listOf(1L))
        computer.iterateUntilFinishedOrWaiting()
        println(computer.outputs)
    }

    fun runPart2() {
        val computer = Computer.from(javaClass.getResource("/input.txt").readText(), listOf(2L))
        computer.iterateUntilFinishedOrWaiting()
        println(computer.outputs)
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}
