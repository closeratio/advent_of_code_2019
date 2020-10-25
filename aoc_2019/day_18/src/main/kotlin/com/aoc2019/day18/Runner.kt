package com.aoc2019.day18

object Runner {

    fun runPart1() {
        val (worldState, maze) = WorldState.parse(javaClass.getResource("/input.txt").readText())

        val planner = Planner(worldState, maze)

        val steps = planner.calculateSteps()
        println(steps)
    }

}


fun main() {
    Runner.runPart1()
}