package com.aoc2019.day16

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestPhase {

    val BASE_PATTERN = Pattern(listOf(0, 1, 0, -1))

    @Test
    fun calculateNextPhaseSimpleInput() {
        val initialPhase = Phase.from(javaClass.getResource("/test_input_1.txt").readText())

        val firstPhase = initialPhase.calculateNextPhase(BASE_PATTERN)
        assertThat(firstPhase, `is`(Phase.from("48226158", 1)))

        val secondPhase = firstPhase.calculateNextPhase(BASE_PATTERN)
        assertThat(secondPhase, `is`(Phase.from("34040438", 2)))

        val thirdPhase = secondPhase.calculateNextPhase(BASE_PATTERN)
        assertThat(thirdPhase, `is`(Phase.from("03415518", 3)))
    }

    @Test
    fun calculateNextPhaseComplexInput1() {
        var phase = Phase.from(javaClass.getResource("/test_input_2.txt").readText())

        repeat(100) {
            phase = phase.calculateNextPhase(BASE_PATTERN)
        }

        assertThat(
                phase.values.take(8),
                `is`(listOf(2, 4, 1, 7, 6, 1, 7, 6))
        )
    }

    @Test
    fun calculateNextPhaseComplexInput2() {
        var phase = Phase.from(javaClass.getResource("/test_input_3.txt").readText())

        repeat(100) {
            phase = phase.calculateNextPhase(BASE_PATTERN)
        }

        assertThat(
                phase.values.take(8),
                `is`(listOf(7, 3, 7, 4, 5, 4, 1, 8))
        )
    }

    @Test
    fun calculateNextPhaseComplexInput3() {
        var phase = Phase.from(javaClass.getResource("/test_input_4.txt").readText())

        repeat(100) {
            phase = phase.calculateNextPhase(BASE_PATTERN)
        }

        assertThat(
                phase.values.take(8),
                `is`(listOf(5, 2, 4, 3, 2, 1, 3, 3))
        )
    }

}