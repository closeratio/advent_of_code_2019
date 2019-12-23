package com.aoc2019.day11

import com.aoc2019.day11.Colour.BLACK
import com.aoc2019.day11.Colour.WHITE

object Runner {

    fun runPart1() {
        val spaceship = Spaceship.from(javaClass.getResource("/input.txt").readText())
        spaceship.paintHull(BLACK)
        println(spaceship.hull.getPaintedPanelCount())
    }

    fun runPart2() {
        val spaceship = Spaceship.from(javaClass.getResource("/input.txt").readText())
        spaceship.paintHull(WHITE)

        println(spaceship.hull.render())
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}