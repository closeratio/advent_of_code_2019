package com.aoc2019.day7

object Runner {

    fun runPart1() {
        val max = AmplifierSet(javaClass.getResource("/input.txt").readText()).computeMaxOutputValue()

        println(max)
    }

}

fun main() {
    Runner.runPart1()
}