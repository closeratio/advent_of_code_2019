package com.aoc2019.day4

import com.aoc2019.day4.PasswordSolver.PasswordValidationStrategy.ANY_DOUBLES
import com.aoc2019.day4.PasswordSolver.PasswordValidationStrategy.STRICT_DOUBLES

object Runner {

    val solver = PasswordSolver(124075, 580769)

    fun runPart1() {
        val result = solver.generateValidPasswords(ANY_DOUBLES)
        println(result)
    }

    fun runPart2() {
        val result = solver.generateValidPasswords(STRICT_DOUBLES)
        println(result)
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}