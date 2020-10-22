package com.aoc2019.day18

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestPlanner {

    @Test
    fun calculateStepsInput1() {
        val planner = Planner(WorldState.parse(javaClass.getResource("/test_input_1.txt").readText()))

        val result = planner.calculateSteps()
        assertThat(result, `is`(86L))
    }

    @Test
    fun calculateStepsInput2() {
        val planner = Planner(WorldState.parse(javaClass.getResource("/test_input_2.txt").readText()))

        val result = planner.calculateSteps()
        assertThat(result, `is`(132L))
    }

    @Test
    fun calculateStepsInput3() {
        val planner = Planner(WorldState.parse(javaClass.getResource("/test_input_3.txt").readText()))

        val result = planner.calculateSteps()
        assertThat(result, `is`(136L))
    }

    @Test
    fun calculateStepsInput4() {
        val planner = Planner(WorldState.parse(javaClass.getResource("/test_input_4.txt").readText()))

        val result = planner.calculateSteps()
        assertThat(result, `is`(81L))
    }

}