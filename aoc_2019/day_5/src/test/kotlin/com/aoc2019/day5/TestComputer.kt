package com.aoc2019.day5

import com.aoc2019.common.computer.Computer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsCollectionContaining.hasItems
import org.junit.Test

class TestComputer {

    private fun getResource(path: String) = javaClass.getResource(path).readText()

    @Test
    fun computer1() {
        val computer = Computer.from(getResource("/test_input_1.txt"))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.program, `is`(arrayOf(1002, 4, 3, 4, 99)))
    }

    @Test
    fun computer2Input0() {
        val computer = Computer.from(getResource("/test_input_2.txt"), listOf(0))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs, hasItems(0))
    }

    @Test
    fun computer2Input5() {
        val computer = Computer.from(getResource("/test_input_2.txt"), listOf(5))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs, hasItems(1))
    }

    @Test
    fun computer2InputMinus3() {
        val computer = Computer.from(getResource("/test_input_2.txt"), listOf(-3))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs, hasItems(1))
    }

    @Test
    fun computer3Input0() {
        val computer = Computer.from(getResource("/test_input_3.txt"), listOf(0))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs, hasItems(0))
    }

    @Test
    fun computer3Input2() {
        val computer = Computer.from(getResource("/test_input_3.txt"), listOf(2))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs, hasItems(1))
    }

    @Test
    fun computer3InputMinus4() {
        val computer = Computer.from(getResource("/test_input_3.txt"), listOf(-4))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs, hasItems(1))
    }

    @Test
    fun computer4Input6() {
        val computer = Computer.from(getResource("/test_input_4.txt"), listOf(6))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs, hasItems(999))
    }

    @Test
    fun computer4Input8() {
        val computer = Computer.from(getResource("/test_input_4.txt"), listOf(8))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs, hasItems(1000))
    }

    @Test
    fun computer4Input11() {
        val computer = Computer.from(getResource("/test_input_4.txt"), listOf(11))
        computer.iterateUntilFinishedOrWaiting()

        assertThat(computer.outputs, hasItems(1001))
    }


}