package com.aco2019.day7

import com.aoc2019.day7.FeedbackAmplifierSet
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestFeedbackAmplifierSet {

    @Test
    fun computeMaxOutputValueForInput4() {
        val max = FeedbackAmplifierSet(javaClass.getResource("/test_input_4.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(9L, 8L, 7L, 6L, 5L)))
        assertThat(max.second, `is`(139629729L))
    }

    @Test
    fun computeMaxOutputValueForInput5() {
        val max = FeedbackAmplifierSet(javaClass.getResource("/test_input_5.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(9L, 7L, 8L, 5L, 6L)))
        assertThat(max.second, `is`(18216L))
    }

}