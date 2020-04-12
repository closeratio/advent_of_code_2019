package com.aco2019.day7

import com.aoc2019.day7.SeriesAmplifierSet
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestSeriesAmplifierSet {

    @Test
    fun computeMaxOutputValueForInput1() {
        val max = SeriesAmplifierSet(javaClass.getResource("/test_input_1.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(4L, 3L, 2L, 1L, 0L)))
        assertThat(max.second, `is`(43210L))
    }

    @Test
    fun computeMaxOutputValueForInput2() {
        val max = SeriesAmplifierSet(javaClass.getResource("/test_input_2.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(0L, 1L, 2L, 3L, 4L)))
        assertThat(max.second, `is`(54321L))
    }

    @Test
    fun computeMaxOutputValueForInput3() {
        val max = SeriesAmplifierSet(javaClass.getResource("/test_input_3.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(1L, 0L, 4L, 3L, 2L)))
        assertThat(max.second, `is`(65210L))
    }


}