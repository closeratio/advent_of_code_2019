package com.aoc2019.day1

object Runner {

    fun runPart1() {
        val total = javaClass.getResource("/input.txt")
                .readText()
                .trim()
                .split("\n")
                .map { it.trim() }
                .map { Module(it.toDouble()) }
                .map { it.baseFuelRequirement() }
                .sum()

        println(total)
    }

    fun runPart2() {
        val total = javaClass.getResource("/input.txt")
                .readText()
                .trim()
                .split("\n")
                .map { it.trim() }
                .map { Module(it.toDouble()) }
                .map { it.totalFuelRequirement() }
                .sum()

        println(total)
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}