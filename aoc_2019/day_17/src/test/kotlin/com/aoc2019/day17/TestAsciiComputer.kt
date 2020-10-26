package com.aoc2019.day17

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class TestAsciiComputer {

    @Test
    fun execute() {
        val computer = AsciiComputer.from(javaClass.getResource("/input_1.txt").readText())
        val result = computer.execute()

        assertThat(result.scaffold, hasSize(greaterThan(0)))
        assertThat(result.alignmentParameters().sum(), greaterThan(0))
    }



}