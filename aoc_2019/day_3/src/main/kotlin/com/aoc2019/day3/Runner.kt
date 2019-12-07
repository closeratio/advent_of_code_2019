package com.aoc2019.day3

import com.aoc2019.common.Vec2i

object Runner {
    val circuit = Circuit.from(javaClass.getResource("/input.txt")
            .readText()
            .lines()
            .map { it.trim() })

    fun runPart1() {
        val nearest = circuit.closestIntersection()
        println(nearest.manhattan(Vec2i.ZERO))
    }

    fun runPart2() {
        val lowest = circuit.lowestStepsToIntersection()
        println(lowest)
    }
}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}