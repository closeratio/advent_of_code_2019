package com.aoc2019.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Test

class TestSpringScript {

    @Test
    fun from() {
        val springScript = SpringScript.from(javaClass.getResource("/test_program_1.txt").readText())

        assertThat(springScript.instructions, hasSize(6))

        assertThat(
            springScript.instructions, `is`(
                listOf(
                    Not('A', 'J'),
                    Not('B', 'T'),
                    And('T', 'J'),
                    Not('C', 'T'),
                    And('T', 'J'),
                    And('D', 'J')
                )
            )
        )
    }

    @Test
    fun walk() {
        val springScript = SpringScript.from(javaClass.getResource("/test_program_2.txt").readText())

        val result = springScript.walk()

        assertThat(result, containsString("Didn't make it across:"))
        assertThat(result, containsString("#####@###########"))
    }

}