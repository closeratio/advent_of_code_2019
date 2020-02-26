package com.aoc2019.day15

import com.aoc2019.common.computer.Computer
import com.aoc2019.common.math.Vec2i.Companion.ZERO
import com.aoc2019.day15.PositionType.OXYGEN_SYSTEM

object Runner {

    val navComputer = NavigationComputer(Computer.from(javaClass.getResource("/input.txt").readText()))

    fun runPart1() {
        navComputer.explore()

        val osPos = navComputer.knownPositions.entries
                .first { it.value == OXYGEN_SYSTEM }
                .key

        val route = navComputer.calculateRoute(ZERO, osPos)
        println(route.size - 1)
    }

    fun runPart2() {
        val result = navComputer.timeToFillWithOxygen()
        println(result)
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}