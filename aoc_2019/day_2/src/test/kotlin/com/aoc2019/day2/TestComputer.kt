package com.aoc2019.day2

import com.aoc2019.common.computer.Computer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestComputer {

    val program = Computer.from(javaClass.getResource("/test_input.txt").readText())

    @Test
    fun from() {
        assertThat(program.program, `is`(arrayOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)))
    }

    @Test
    fun iterate() {

        program.iterate()
        assertThat(program.program, `is`(arrayOf(1, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)))

        program.iterate()
        assertThat(program.program, `is`(arrayOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)))

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
                Computer.from("1,0,0,0,99").iterateUntilFinished().program,
                `is`(arrayOf(2, 0, 0, 0, 99))
        )

        assertThat(
                Computer.from("2,3,0,3,99").iterateUntilFinished().program,
                `is`(arrayOf(2, 3, 0, 6, 99))
        )

        assertThat(
                Computer.from("2,4,4,5,99,0").iterateUntilFinished().program,
                `is`(arrayOf(2, 4, 4, 5, 99, 9801))
        )

        assertThat(
                Computer.from("1,1,1,4,99,5,6,0,99").iterateUntilFinished().program,
                `is`(arrayOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
        )
    }

}