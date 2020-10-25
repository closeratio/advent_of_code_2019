package com.aoc2019.day18

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class TestPlanner {

    companion object {
        @JvmStatic
        private fun getTestData(): Stream<Arguments> = listOf(
                Arguments.of("/test_input_1.txt", 86L),
                Arguments.of("/test_input_2.txt", 132L),
                Arguments.of("/test_input_3.txt", 136L),
                Arguments.of("/test_input_4.txt", 81L),
                Arguments.of("/test_input_5.txt", 8L),
                Arguments.of("/test_input_6.txt", 24L),
                Arguments.of("/test_input_7.txt", 32L),
                Arguments.of("/test_input_8.txt", 72L)
        ).stream()
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    fun calculateStepsInput1(source: String, expectedSteps: Long) {
        val (worldState, maze) = WorldState.parse(javaClass.getResource(source).readText())
        val planner = Planner(worldState, maze)

        val result = planner.calculateSteps()
        assertThat(result, `is`(expectedSteps))
    }

}