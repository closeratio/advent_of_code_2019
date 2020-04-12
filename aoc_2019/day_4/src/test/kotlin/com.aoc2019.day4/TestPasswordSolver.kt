package com.aoc2019.day4

import com.aoc2019.day4.PasswordSolver.PasswordValidationStrategy.ANY_DOUBLES
import com.aoc2019.day4.PasswordSolver.PasswordValidationStrategy.STRICT_DOUBLES
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestPasswordSolver {

    val solver = PasswordSolver(0, 999_999)

    @Test
    fun passwordValue() {
        assertThat(arrayOf(1, 2, 3).passwordValue(), `is`(123))
        assertThat(arrayOf(3, 0, 0, 4, 1, 2).passwordValue(), `is`(300_412))
    }

    @Test
    fun isPasswordValidAnyDoubles() {
        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 5, 5), ANY_DOUBLES), `is`(true))

        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 5, 6), ANY_DOUBLES), `is`(false))
        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 4, 2), ANY_DOUBLES), `is`(false))

        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 4, 4), ANY_DOUBLES), `is`(true))
    }

    @Test
    fun isPasswordValidStrictDoubles() {
        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 5, 5), STRICT_DOUBLES), `is`(true))

        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 5, 6), STRICT_DOUBLES), `is`(false))
        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 4, 2), STRICT_DOUBLES), `is`(false))

        assertThat(solver.isPasswordValid(arrayOf(1, 1, 2, 2, 3, 3), STRICT_DOUBLES), `is`(true))

        assertThat(solver.isPasswordValid(arrayOf(1, 2, 3, 4, 4, 4), STRICT_DOUBLES), `is`(false))
        assertThat(solver.isPasswordValid(arrayOf(1, 1, 1, 1, 2, 2), STRICT_DOUBLES), `is`(true))
    }

}