package com.aoc2019.day2

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestProgram {

    val program = Program.from(javaClass.getResource("/test_input.txt").readText())

    @Test
    fun from() {
        assertThat(program.state, `is`(arrayOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)))
    }

    @Test
    fun iterate() {

        program.iterate()
        assertThat(program.state, `is`(arrayOf(1, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)))

        program.iterate()
        assertThat(program.state, `is`(arrayOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)))

        program.iterate()
        assertThat(program.finished, `is`(true))
    }

    @Test
    fun iterateUntilFinished() {
        program.iterateUntilFinished()

        assertThat(program.programCounter, `is`(8))
    }

    @Test
    fun smallerPrograms() {
        assertThat(
                Program.from("1,0,0,0,99").iterateUntilFinished().state,
                `is`(arrayOf(2, 0, 0, 0, 99))
        )

        assertThat(
                Program.from("2,3,0,3,99").iterateUntilFinished().state,
                `is`(arrayOf(2, 3, 0, 6, 99))
        )

        assertThat(
                Program.from("2,4,4,5,99,0").iterateUntilFinished().state,
                `is`(arrayOf(2, 4, 4, 5, 99, 9801))
        )

        assertThat(
                Program.from("1,1,1,4,99,5,6,0,99").iterateUntilFinished().state,
                `is`(arrayOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
        )
    }

}