package com.aoc2019.day10

object Runner {

    fun runPart1() {
        val asteroidBelt = AsteroidBelt.from(javaClass.getResource("/input.txt").readText())

        println(asteroidBelt.getOptimalPosition())
    }

    fun runPart2() {
        val asteroidBelt = AsteroidBelt.from(javaClass.getResource("/input.txt").readText())
        val optimal = asteroidBelt.getOptimalPosition()
        val order = asteroidBelt.getAsteroidDestructionOrder(Asteroid(optimal.first))

        val targetAsteroid = order[199]
        println((targetAsteroid.position.x * 100) + targetAsteroid.position.y)
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}