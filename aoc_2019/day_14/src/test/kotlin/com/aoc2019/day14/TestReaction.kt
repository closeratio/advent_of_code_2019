package com.aoc2019.day14

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class TestReaction {

    private val reaction = Reaction(
            setOf(ChemicalAmount(Chemical(ChemicalId("INPUT")), 3L)),
            ChemicalAmount(Chemical(ChemicalId("OUTPUT")), 5L)
    )

    @Test
    fun getIterationsNeededToProduce() {
        assertThat(reaction.getIterationsNeededToProduce(1L), `is`(1L))
        assertThat(reaction.getIterationsNeededToProduce(2L), `is`(1L))
        assertThat(reaction.getIterationsNeededToProduce(3L), `is`(1L))
        assertThat(reaction.getIterationsNeededToProduce(4L), `is`(1L))
        assertThat(reaction.getIterationsNeededToProduce(5L), `is`(1L))

        assertThat(reaction.getIterationsNeededToProduce(6L), `is`(2L))
        assertThat(reaction.getIterationsNeededToProduce(7L), `is`(2L))
        assertThat(reaction.getIterationsNeededToProduce(8L), `is`(2L))
        assertThat(reaction.getIterationsNeededToProduce(9L), `is`(2L))
        assertThat(reaction.getIterationsNeededToProduce(10L), `is`(2L))
    }

}