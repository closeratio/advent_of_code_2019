package com.aoc2019.day16

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestSignalDecoder {

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
        assertThat(decoderForInput1.calculateMessage(), `is`("84462026"))
        assertThat(decoderForInput2.calculateMessage(), `is`("78725270"))
        assertThat(decoderForInput3.calculateMessage(), `is`("53553731"))
    }

}