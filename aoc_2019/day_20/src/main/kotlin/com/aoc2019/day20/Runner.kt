package com.aoc2019.day20

object Runner {

    fun runPart1() {
        val maze = Maze.from(javaClass.getResource("/input.txt").readText())
        println(maze.calculateShortestRoute())
    }

    fun runPart2() {
        val maze = Maze.from(javaClass.getResource("/input.txt").readText())
        println(maze.calculateShortestRoute(true))
    }

}

fun main() {
    Runner.runPart2()
}