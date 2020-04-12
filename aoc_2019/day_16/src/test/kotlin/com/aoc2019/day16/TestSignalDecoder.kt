package com.aoc2019.day16

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestSignalDecoder {

    val BASE_PATTERN = Pattern(listOf(0, 1, 0, -1))

    private val decoderForInput1 = SignalDecoder(Phase.from(
            javaClass.getResource("/test_input_5.txt").readText(),
            repeat = 10_000
    ))

    private val decoderForInput2 = SignalDecoder(Phase.from(
            javaClass.getResource("/test_input_6.txt").readText(),
            repeat = 10_000
    ))

    private val decoderForInput3 = SignalDecoder(Phase.from(
            javaClass.getResource("/test_input_7.txt").readText(),
            repeat = 10_000
    ))

    @Test
    fun initialOffset() {
        assertThat(decoderForInput1.offset, `is`(303673))
        assertThat(decoderForInput2.offset, `is`(293510))
        assertThat(decoderForInput3.offset, `is`(308177))
    }

    @Test
    fun calculateMessage() {
        assertThat(decoderForInput1.calculateMessage(BASE_PATTERN), `is`("84462026"))
    }

}