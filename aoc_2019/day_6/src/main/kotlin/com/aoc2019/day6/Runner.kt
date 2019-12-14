package com.aoc2019.day6

object Runner {

    fun runPart1() {
        val solarSystem = SolarSystem.from(javaClass.getResource("/input.txt").readText())

        println(solarSystem.totalOrbits())
    }

}

fun main() {
    Runner.runPart1()
}