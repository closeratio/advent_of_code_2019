package com.aoc2019.day9

import com.aoc2019.common.computer.Computer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestComputer {

    @Test
    fun input1() {
        val computer = Computer.from(javaClass.getResource("/test_input_1.txt").readText())
        computer.iterateUntilFinishedOrWaiting()

        val expected = Computer.from(javaClass.getResource("/test_input_1.txt").readText()).memory.values()
        assertThat(computer.outputs, `is`(expected))
    }

    @Test
    fun input2() {
        val computer = Computer.from(javaClass.getResource("/test_input_2.txt").readText())
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs.size, `is`(1))
        assertThat(computer.outputs[0].toString().length, `is`(16))
    }

    @Test
    fun input3() {
        val computer = Computer.from(javaClass.getResource("/test_input_3.txt").readText())
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs.size, `is`(1))
        assertThat(computer.outputs[0], `is`(1125899906842624L))
    }

}