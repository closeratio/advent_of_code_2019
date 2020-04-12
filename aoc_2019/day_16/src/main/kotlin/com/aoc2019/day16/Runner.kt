package com.aoc2019.day16

object Runner {

    val BASE_PATTERN = Pattern(listOf(0, 1, 0, -1))

    fun runPart1() {
        var phase = Phase(
                0,
                javaClass.getResource("/input.txt").readText().map { it.toString().toInt() }
        )

        repeat(100) {
            phase = phase.calculateNextPhase(BASE_PATTERN)
        }

        println(phase.values.take(8).joinToString(""))
    }
}

fun main() {
    Runner.runPart1()
}