package com.aco2019.day7

import com.aoc2019.day7.SeriesAmplifierSet
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestSeriesAmplifierSet {

    @Test
    fun computeMaxOutputValueForInput1() {
        val max = SeriesAmplifierSet(javaClass.getResource("/test_input_1.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(4, 3, 2, 1, 0)))
        assertThat(max.second, `is`(43210))
    }

    @Test
    fun computeMaxOutputValueForInput2() {
        val max = SeriesAmplifierSet(javaClass.getResource("/test_input_2.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(0, 1, 2, 3, 4)))
        assertThat(max.second, `is`(54321))
    }

    @Test
    fun computeMaxOutputValueForInput3() {
        val max = SeriesAmplifierSet(javaClass.getResource("/test_input_3.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(1, 0, 4, 3, 2)))
        assertThat(max.second, `is`(65210))
    }


}