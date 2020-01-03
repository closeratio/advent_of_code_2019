package com.aoc2019.day12

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestMoonStateSimulation {

    @Test
    fun from() {
        val sim = MoonSimulation.from(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(sim.initialState(), `is`(MoonSimulation.fromState(javaClass
                .getResource("/test_input_1_0_step_state.txt").readText())))
    }

    @Test
    fun calculateNextStep() {
        val sim = MoonSimulation.from(javaClass.getResource("/test_input_1.txt").readText())

        sim.calculateNextState()
        assertThat(sim.lastState(), `is`(MoonSimulation.fromState(javaClass
                .getResource("/test_input_1_1_step_state.txt").readText())))

        sim.calculateNextState()
        assertThat(sim.lastState(), `is`(MoonSimulation.fromState(javaClass
                .getResource("/test_input_1_2_step_state.txt").readText())))

        sim.calculateNextState()
        assertThat(sim.lastState(), `is`(MoonSimulation.fromState(javaClass
                .getResource("/test_input_1_3_step_state.txt").readText())))

    }

    @Test
    fun calculateNextStep10IterationsInput1() {
        val sim = MoonSimulation.from(javaClass.getResource("/test_input_1.txt").readText())

        repeat(10) {
            sim.calculateNextState()
        }

        assertThat(sim.lastState(), `is`(MoonSimulation.fromState(javaClass
                .getResource("/test_input_1_10_step_state.txt").readText())))

        assertThat(sim.calculateSystemEnergy(), `is`(179))
    }

    @Test
    fun calculateNextStep10IterationsInput2() {
        val sim = MoonSimulation.from(javaClass.getResource("/test_input_2.txt").readText())

        assertThat(sim.lastState(), `is`(MoonSimulation.fromState(javaClass
                .getResource("/test_input_2_0_step_state.txt").readText())))

        repeat(10) {
            sim.calculateNextState()
        }

        assertThat(sim.lastState(), `is`(MoonSimulation.fromState(javaClass
                .getResource("/test_input_2_10_step_state.txt").readText())))
    }

    @Test
    fun calculateNextStep100IterationsInput2() {
        val sim = MoonSimulation.from(javaClass.getResource("/test_input_2.txt").readText())

        repeat(100) {
            sim.calculateNextState()
        }

        assertThat(sim.lastState(), `is`(MoonSimulation.fromState(javaClass
                .getResource("/test_input_2_100_step_state.txt").readText())))

        assertThat(sim.calculateSystemEnergy(), `is`(1940))
    }

    @Test
    fun findPeriodInput1() {
        val sim = MoonSimulation.from(javaClass.getResource("/test_input_1.txt").readText())

        val result = sim.findPeriod()

        assertThat(result, `is`(2772L))
    }

    @Test
    fun findPeriodInput2() {
        val sim = MoonSimulation.from(javaClass.getResource("/test_input_2.txt").readText())

        val result = sim.findPeriod()

        assertThat(result, `is`(4686774924L))
    }
}