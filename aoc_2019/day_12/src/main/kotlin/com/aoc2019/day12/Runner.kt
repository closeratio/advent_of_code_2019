package com.aoc2019.day12

object Runner {

    fun runPart1() {
        val moonSimulation = MoonSimulation.from(javaClass.getResource("/input.txt").readText())

        repeat(1000) {
            moonSimulation.calculateNextState()
        }

        println(moonSimulation.calculateSystemEnergy())
    }

}

fun main() {
    Runner.runPart1()
}