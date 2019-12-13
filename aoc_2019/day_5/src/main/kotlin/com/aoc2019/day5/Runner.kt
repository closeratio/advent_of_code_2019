package com.aoc2019.day5

import com.aoc2019.common.computer.Computer

object Runner {

    fun runPart1() {
        val computer = Computer.from(javaClass.getResource("/input.txt").readText(), arrayListOf(1))

        computer.iterateUntilFinished()

        println(computer.outputs)
    }

}

fun main() {
    Runner.runPart1()
}