package com.aoc2019.day14

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestNanofactory {

    @Test
    fun fromInput1() {
        val factory = Nanofactory.from(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(factory.chemicals.size, `is`(7))
        assertThat(factory.reactions.size, `is`(6))
    }

    @Test
    fun fromInput2() {
        val factory = Nanofactory.from(javaClass.getResource("/test_input_2.txt").readText())

        assertThat(factory.chemicals.size, `is`(8))
        assertThat(factory.reactions.size, `is`(7))
    }

    @Test
    fun fromInput3() {
        val factory = Nanofactory.from(javaClass.getResource("/test_input_3.txt").readText())

        assertThat(factory.chemicals.size, `is`(10))
        assertThat(factory.reactions.size, `is`(9))
    }

    @Test
    fun getRequiredAmountInput1() {
        val factory = Nanofactory.from(javaClass.getResource("/test_input_1.txt").readText())
        val manufactured = factory.manufactureChemical(ChemicalId("FUEL"), 1L)

        assertThat(manufactured.getValue(ChemicalId("ORE")), `is`(31L))
    }

    @Test
    fun getRequiredAmountInput2() {
        val factory = Nanofactory.from(javaClass.getResource("/test_input_2.txt").readText())
        val manufactured = factory.manufactureChemical(ChemicalId("FUEL"), 1L)

        assertThat(manufactured.getValue(ChemicalId("ORE")), `is`(165L))
    }

    @Test
    fun getRequiredAmountInput3() {
        val factory = Nanofactory.from(javaClass.getResource("/test_input_3.txt").readText())
        val manufactured = factory.manufactureChemical(ChemicalId("FUEL"), 1L)

        assertThat(manufactured.getValue(ChemicalId("ORE")), `is`(13312L))
    }

    @Test
    fun getRequiredAmountInput4() {
        val factory = Nanofactory.from(javaClass.getResource("/test_input_4.txt").readText())
        val manufactured = factory.manufactureChemical(ChemicalId("FUEL"), 1L)

        assertThat(manufactured.getValue(ChemicalId("ORE")), `is`(180697L))
    }

    @Test
    fun getRequiredAmountInput5() {
        val factory = Nanofactory.from(javaClass.getResource("/test_input_5.txt").readText())
        val manufactured = factory.manufactureChemical(ChemicalId("FUEL"), 1L)

        assertThat(manufactured.getValue(ChemicalId("ORE")), `is`(2210736L))
    }

}