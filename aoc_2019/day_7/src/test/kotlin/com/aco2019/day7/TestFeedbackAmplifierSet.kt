package com.aco2019.day7

import com.aoc2019.day7.FeedbackAmplifierSet
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestFeedbackAmplifierSet {

    @Test
    fun computeMaxOutputValueForInput4() {
        val max = FeedbackAmplifierSet(javaClass.getResource("/test_input_4.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(9, 8, 7, 6, 5)))
        assertThat(max.second, `is`(139629729))
    }

    @Test
    fun computeMaxOutputValueForInput5() {
        val max = FeedbackAmplifierSet(javaClass.getResource("/test_input_5.txt").readText()).computeMaxOutputValue()

        assertThat(max.first, `is`(listOf(9, 7, 8, 5, 6)))
        assertThat(max.second, `is`(18216))
    }

}