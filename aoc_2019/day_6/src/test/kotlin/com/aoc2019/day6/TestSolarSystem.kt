package com.aoc2019.day6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.hamcrest.core.IsCollectionContaining.hasItems
import org.junit.Test

class TestSolarSystem {

    val solarSystem = SolarSystem.from(javaClass.getResource("/test_input_1.txt").readText())

    @Test
    fun from() {
        val planets = solarSystem.planets

        assertThat(planets.size, `is`(12))

        val root = planets.find { it.id.id == "COM" }!!
        assertThat(root.parent, nullValue())
        assertThat(root.children.map { it.id }, hasItems(PlanetID("B")))
    }

    @Test
    fun totalOrbits() {
        val totalOrbits: Int = solarSystem.totalOrbits()

        assertThat(totalOrbits, `is`(42))
    }

    @Test
    fun transfersToSanta() {
        val santaSystem = SolarSystem.from(javaClass.getResource("/test_input_2.txt").readText())

        assertThat(santaSystem.transfersToSanta(), `is`(4))
    }

}