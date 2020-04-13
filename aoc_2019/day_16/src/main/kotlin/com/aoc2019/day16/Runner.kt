package com.aoc2019.day16

object Runner {

    val BASE_PATTERN = Pattern(listOf(0, 1, 0, -1))

    fun runPart1() {
        var phase = Phase.from(javaClass.getResource("/input.txt").readText())

        repeat(100) {
            phase = phase.calculateNextPhase(BASE_PATTERN)
        }

        println(phase.values.take(8).joinToString(""))
    }

    fun runPart2() {
        val decoder = SignalDecoder(Phase.from(
                javaClass.getResource("/input.txt").readText(),
                repeat = 10_000
        ))

        val message = decoder.calculateMessage()

        println(message)
    }
}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}