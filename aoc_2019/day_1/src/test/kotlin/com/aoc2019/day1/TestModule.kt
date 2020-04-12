package com.aoc2019.day1

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestModule {

    @Test
    fun baseFuelRequirement() {
        assertThat(Module(12).baseFuelRequirement(), `is`(2.0))
        assertThat(Module(14).baseFuelRequirement(), `is`(2.0))
        assertThat(Module(1969).baseFuelRequirement(), `is`(654.0))
        assertThat(Module(100756).baseFuelRequirement(), `is`(33583.0))
    }

    @Test
    fun totalFuelRequirement() {
        assertThat(Module(14).totalFuelRequirement(), `is`(2.0))
        assertThat(Module(1969).totalFuelRequirement(), `is`(966.0))
        assertThat(Module(100756).totalFuelRequirement(), `is`(50346.0))
    }

}
