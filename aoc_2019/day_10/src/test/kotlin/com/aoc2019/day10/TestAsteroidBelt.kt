package com.aoc2019.day10

import com.aoc2019.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsIterableContaining.hasItems
import org.junit.jupiter.api.Test

class TestAsteroidBelt {

    @Test
    fun from() {
        val belt = AsteroidBelt.from(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(belt.asteroids.size, `is`(10))

        val expected: List<Asteroid> = listOf(
                Vec2i(1, 0),
                Vec2i(4, 0),
                Vec2i(0, 2),
                Vec2i(1, 2),
                Vec2i(2, 2),
                Vec2i(3, 2),
                Vec2i(4, 2),
                Vec2i(4, 3),
                Vec2i(3, 4),
                Vec2i(4, 4)
        ).map { Asteroid(it) }

        assertThat(
                belt.asteroids.toList(),
                hasItems(*expected.toTypedArray())
        )
    }

    @Test
    fun getOptimalPositionInput1() {
        val belt = AsteroidBelt.from(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(belt.getOptimalPosition(), `is`(Vec2i(3, 4) to 8))
    }

    @Test
    fun getOptimalPositionInput2() {
        val belt = AsteroidBelt.from(javaClass.getResource("/test_input_2.txt").readText())

        assertThat(belt.getOptimalPosition(), `is`(Vec2i(5, 8) to 33))
    }

    @Test
    fun getOptimalPositionInput3() {
        val belt = AsteroidBelt.from(javaClass.getResource("/test_input_3.txt").readText())

        assertThat(belt.getOptimalPosition(), `is`(Vec2i(1, 2) to 35))
    }

    @Test
    fun getOptimalPositionInput4() {
        val belt = AsteroidBelt.from(javaClass.getResource("/test_input_4.txt").readText())

        assertThat(belt.getOptimalPosition(), `is`(Vec2i(6, 3) to 41))
    }

    @Test
    fun getOptimalPositionInput5() {
        val belt = AsteroidBelt.from(javaClass.getResource("/test_input_5.txt").readText())

        assertThat(belt.getOptimalPosition(), `is`(Vec2i(11, 13) to 210))
    }

    @Test
    fun getNthDestroyedAsteroidPositionInput5() {
        val belt = AsteroidBelt.from(javaClass.getResource("/test_input_5.txt").readText())

        val order = belt.getAsteroidDestructionOrder(Asteroid(Vec2i(11, 13)))

        assertThat(order[199].position, `is`(Vec2i(8, 2)))
    }

    @Test
    fun getNthDestroyedAsteroidPositionInput6() {
        val belt = AsteroidBelt.from(javaClass.getResource("/test_input_6.txt").readText())

        val order = belt.getAsteroidDestructionOrder(Asteroid(Vec2i(8, 3)))

        assertThat(order[8].position, `is`(Vec2i(15, 1)))
    }


}