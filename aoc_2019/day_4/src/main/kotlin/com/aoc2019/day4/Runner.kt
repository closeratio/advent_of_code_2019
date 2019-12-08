package com.aoc2019.day4

object Runner {

    fun runPart1() {
        val solver = PasswordSolver(124075, 580769)
        val result = solver.generateValidPasswords()
        println(result)
    }

}

fun main() {
    Runner.runPart1()
}