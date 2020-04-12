package com.aoc2019.day8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestImage {

    @Test
    fun from() {
        val image = Image.from(
                javaClass.getResource("/test_input_1.txt").readText().trim(),
                3,
                2
        )

        assertThat(image.layers.size, `is`(2))

        assertThat(image.layers[0].rows, `is`(
                listOf(
                        listOf(1, 2, 3),
                        listOf(4, 5, 6)
                )
        ))

        assertThat(image.layers[1].rows, `is`(
                listOf(
                        listOf(7, 8, 9),
                        listOf(0, 1, 2)
                )
        ))
    }

    @Test
    fun render() {
        val image = Image.from(
                javaClass.getResource("/test_input_2.txt").readText().trim(),
                2,
                2
        )

        val rendered = image.render()

        assertThat(rendered, `is`(
                listOf(
                        "01",
                        "10"
                )
        ))
    }

}