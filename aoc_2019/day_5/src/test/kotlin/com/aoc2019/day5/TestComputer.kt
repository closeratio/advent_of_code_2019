package com.aoc2019.day5

import com.aoc2019.common.computer.Computer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestComputer {

    val computer = Computer.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun iterateUntilFinished() {
        computer.iterateUntilFinished()

        assertThat(computer.program, `is`(arrayOf(1002, 4, 3, 4, 99)))
    }

}