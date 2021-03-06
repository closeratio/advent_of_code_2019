package com.aoc2019.day16

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestPattern {

    @Test
    fun calculateOutputPattern() {
        val outputPattern = Pattern(listOf(0, 1, 0, -1)).calculateOutputPattern(1, 15)

        assertThat(outputPattern.dropLast(1), `is`(listOf(
                0, 1, 1, 0, 0, -1, -1, 0, 0, 1, 1, 0, 0, -1, -1
        )))
    }

}