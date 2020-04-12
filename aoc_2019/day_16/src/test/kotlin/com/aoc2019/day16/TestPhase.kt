package com.aoc2019.day16

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestPhase {

    val BASE_PATTERN = Pattern(listOf(0, 1, 0, -1))

    @Test
    fun calculateNextPhaseSimpleInput() {
        val initialPhase = Phase(
                0,
                javaClass.getResource("/test_input_1.txt").readText().map { it.toString().toInt() }
        )

        val firstPhase = initialPhase.calculateNextPhase(BASE_PATTERN)

        assertThat(firstPhase, `is`(Phase(
                1,
                listOf(4, 8, 2, 2, 6, 1, 5, 8)
        )))

        val secondPhase = firstPhase.calculateNextPhase(BASE_PATTERN)

        assertThat(secondPhase, `is`(Phase(
                2,
                listOf(3, 4, 0, 4, 0, 4, 3, 8)
        )))

        val thirdPhase = secondPhase.calculateNextPhase(BASE_PATTERN)

        assertThat(thirdPhase, `is`(Phase(
                3,
                listOf(0, 3, 4, 1, 5, 5, 1, 8)
        )))
    }

    @Test
    fun calculateNextPhaseComplexInput1() {
        var phase = Phase(
                0,
                javaClass.getResource("/test_input_2.txt").readText().map { it.toString().toInt() }
        )

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
        var phase = Phase(
                0,
                javaClass.getResource("/test_input_3.txt").readText().map { it.toString().toInt() }
        )

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
        var phase = Phase(
                0,
                javaClass.getResource("/test_input_4.txt").readText().map { it.toString().toInt() }
        )

        repeat(100) {
            phase = phase.calculateNextPhase(BASE_PATTERN)
        }

        assertThat(
                phase.values.take(8),
                `is`(listOf(5, 2, 4, 3, 2, 1, 3, 3))
        )
    }

}