package com.aoc2019.day19

object Runner {

    fun runPart1() {
        val result = TractorBeam(
                javaClass.getResource("/input.txt").readText()
        ).computeAffectedPoints()

        println(result)
    }

    fun runPart2() {
        val result = TractorBeam(
                javaClass.getResource("/input.txt").readText()
        ).findClosestFittingPoint()

        println(result)
    }

}

fun main() {
    Runner.runPart2()
}