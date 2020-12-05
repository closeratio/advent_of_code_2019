package com.aoc2019.day22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TestCardStack {

    private val stack = CardStack((0L..9L).map {
        SpaceCard(it)
    })

    @Test
    fun dealToNewStack() {
        val newStack = stack.dealToNewStack()

        assertThat(
            newStack.cards, `is`(listOf(
                9, 8, 7, 6, 5, 4, 3, 2, 1, 0
            ).map { SpaceCard(it.toLong()) })
        )
    }

    @Test
    fun cutPositive() {
        val newStack = stack.cut(3)

        assertThat(
            newStack.cards, `is`(listOf(
                3, 4, 5, 6, 7, 8, 9, 0, 1, 2
            ).map { SpaceCard(it.toLong()) })
        )
    }

    @Test
    fun cutNegative() {
        val newStack = stack.cut(-4)

        assertThat(
            newStack.cards, `is`(listOf(
                6, 7, 8, 9, 0, 1, 2, 3, 4, 5
            ).map { SpaceCard(it.toLong()) })
        )
    }

    @Test
    fun dealWithIncrement() {
        val newStack = stack.dealWithIncrement(3)

        assertThat(
            newStack.cards, `is`(listOf(
                0, 7, 4, 1, 8, 5, 2, 9, 6, 3
            ).map { SpaceCard(it.toLong()) })
        )
    }

    @ParameterizedTest
    @MethodSource("getFactoryOrderArguments")
    fun factoryOrder(
        instructions: List<String>,
        expected: List<Long>
    ) {
        val result = stack.processInstructions(instructions)

        assertThat(
            result.cards, `is`(expected.map { SpaceCard(it) })
        )
    }

    companion object {

        @JvmStatic
        fun getFactoryOrderArguments(): List<Arguments> = listOf(
            "/test_input_1.txt",
            "/test_input_2.txt",
            "/test_input_3.txt",
            "/test_input_4.txt"
        ).map { TestCardStack::class.java.getResource(it).readText() }
            .map { text ->
                text.trim().split("\n").map { line -> line.trim() }
            }
            .map { text ->
                val instructions = text.dropLast(1)
                val expected = text.last()
                    .split(" ")
                    .drop(1)
                    .map { it.toLong() }

                Arguments.of(instructions, expected)
            }

    }

}