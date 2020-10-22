package com.aoc2019.day18

object Runner {

    fun runPart1() {
        val planner = Planner(WorldState.parse(javaClass.getResource("/input.txt").readText()))

        val steps = planner.calculateSteps()
        println(steps)
    }

}


fun main() {
    Runner.runPart1()
}