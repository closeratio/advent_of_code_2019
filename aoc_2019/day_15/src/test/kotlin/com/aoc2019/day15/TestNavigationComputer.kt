package com.aoc2019.day15

import com.aoc2019.common.computer.Computer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.greaterThan
import org.junit.Test

class TestNavigationComputer {

    val computer = NavigationComputer(Computer.from(javaClass.getResource("/input.txt").readText()))

    @Test
    fun setup() {
        assertThat(computer.knownPositions.size, `is`(5))
    }

    @Test
    fun explore() {
        computer.explore()

        assertThat(computer.knownPositions.size, greaterThan(10))
    }

}