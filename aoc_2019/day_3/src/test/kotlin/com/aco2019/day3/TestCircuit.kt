package com.aco2019.day3

import com.aoc2019.common.math.Vec2i
import com.aoc2019.common.math.Vec2i.Companion.ZERO
import com.aoc2019.day3.Circuit
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Test

class TestCircuit {

    val circuit = Circuit.from(javaClass
            .getResource("/test_input_1.txt")
            .readText()
            .lines()
            .map { it.trim() })

    @Test
    fun from() {
        assertThat(circuit.firstWirePositions.size, `is`(22))
        assertThat(circuit.firstWirePositions[8], `is`(Vec2i(8, 0)))
        assertThat(circuit.firstWirePositions[13], `is`(Vec2i(8, -5)))
        assertThat(circuit.firstWirePositions[18], `is`(Vec2i(3, -5)))
        assertThat(circuit.firstWirePositions[21], `is`(Vec2i(3, -2)))

        assertThat(circuit.secondWirePositions.size, `is`(22))
        assertThat(circuit.secondWirePositions[7], `is`(Vec2i(0, -7)))
        assertThat(circuit.secondWirePositions[13], `is`(Vec2i(6, -7)))
        assertThat(circuit.secondWirePositions[17], `is`(Vec2i(6, -3)))
        assertThat(circuit.secondWirePositions[21], `is`(Vec2i(2, -3)))
    }

    @Test
    fun intersectionPoints() {
        val points: Set<Vec2i> = circuit.intersectionPoints()

        assertThat(points.size, `is`(3))
        assertThat(points, hasItems(ZERO, Vec2i(3, -3), Vec2i(6, -5)))
    }

    @Test
    fun closestIntersection() {
        val closest: Vec2i = circuit.closestIntersection()

        assertThat(closest, `is`(Vec2i(3, -3)))
        assertThat(closest.manhattan(ZERO).toInt(), `is`(6))
    }

    @Test
    fun complexInputs() {
        val circuit1 = Circuit.from(javaClass.getResource("/test_input_2.txt")
                .readText()
                .lines()
                .map { it.trim() })
        assertThat(circuit1.closestIntersection().manhattan(ZERO).toInt(), `is`(159))

        val circuit2 = Circuit.from(javaClass.getResource("/test_input_3.txt")
                .readText()
                .lines()
                .map { it.trim() })
        assertThat(circuit2.closestIntersection().manhattan(ZERO).toInt(), `is`(135))
    }

    @Test
    fun lowestStepsToIntersection() {
        assertThat(circuit.lowestStepsToIntersection(), `is`(30))

        val steps1: Int = Circuit.from(javaClass.getResource("/test_input_2.txt")
                .readText()
                .lines()
                .map { it.trim() })
                .lowestStepsToIntersection()

        assertThat(steps1, `is`(610))

        val steps2: Int = Circuit.from(javaClass.getResource("/test_input_3.txt")
                .readText()
                .lines()
                .map { it.trim() })
                .lowestStepsToIntersection()

        assertThat(steps2, `is`(410))
    }

}
