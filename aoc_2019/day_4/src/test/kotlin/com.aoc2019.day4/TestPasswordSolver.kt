package com.aoc2019.day4

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestPasswordSolver {

    val solver = PasswordSolver(0, 999_999)

    @Test
    fun passwordValue() {
        assertThat(arrayOf(1, 2, 3).passwordValue(), `is`(123))
        assertThat(arrayOf(3, 0, 0, 4, 1, 2).passwordValue(), `is`(300_412))
    }

    @Test
    fun isPasswordValid() {
        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 5, 5)), `is`(true))

        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 5, 6)), `is`(false))
        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 4, 2)), `is`(false))

        assertThat(solver.isPasswordValid(arrayOf(1, 1, 2, 2, 3, 3)), `is`(true))

        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 4, 4)), `is`(false))
        assertThat(solver.isPasswordValid(arrayOf(1, 1, 1, 1, 2, 2)), `is`(false))
    }

}