package com.aoc2019.day12

object Runner {

    fun runPart1() {
        val moonSimulation = MoonSimulation.from(javaClass.getResource("/input.txt").readText())

        repeat(1000) {
            moonSimulation.calculateNextState()
        }

        println(moonSimulation.calculateSystemEnergy())
    }

    fun runPart2() {
        val moonSimulation = MoonSimulation.from(javaClass.getResource("/input.txt").readText())

        println(moonSimulation.findPeriod())
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}