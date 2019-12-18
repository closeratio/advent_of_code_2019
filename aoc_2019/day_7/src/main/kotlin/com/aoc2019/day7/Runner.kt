package com.aoc2019.day7

object Runner {

    fun runPart1() {
        val max = SeriesAmplifierSet(javaClass.getResource("/input.txt").readText()).computeMaxOutputValue()

        println(max)
    }

    fun runPart2() {
        val max = FeedbackAmplifierSet(javaClass.getResource("/input.txt").readText()).computeMaxOutputValue()

        println(max)
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}