package com.aoc2019.day10

object Runner {

    fun runPart1() {
        val asteroidBelt = AsteroidBelt.from(javaClass.getResource("/input.txt").readText())

        println(asteroidBelt.getOptimalPosition())
    }

}

fun main() {
    Runner.runPart1()
}