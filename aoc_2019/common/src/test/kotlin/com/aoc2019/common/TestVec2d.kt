package com.aoc2019.common

import com.aoc2019.common.math.Vec2d
import com.aoc2019.common.math.Vec2d.Companion.ZERO
import com.aoc2019.common.math.toDegrees
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TestVec2d {

    @Test
    fun angle0() {
        assertThat(ZERO.angleTo(Vec2d(0, -1)).toDegrees(), `is`(0.0))
    }

    @Test
    fun angle45() {
        assertThat(ZERO.angleTo(Vec2d(1, -1)).toDegrees(), `is`(45.0))
    }

    @Test
    fun angle90() {
        assertThat(ZERO.angleTo(Vec2d(1, 0)).toDegrees(), `is`(90.0))
    }

    @Test
    fun angle135() {
        assertThat(ZERO.angleTo(Vec2d(1, 1)).toDegrees(), `is`(135.0))
    }

    @Test
    fun angle180() {
        assertThat(ZERO.angleTo(Vec2d(0, 1)).toDegrees(), `is`(180.0))
    }

    @Test
    fun angle225() {
        assertThat(ZERO.angleTo(Vec2d(-1, 1)).toDegrees(), `is`(225.0))
    }

    @Test
    fun angle270() {
        assertThat(ZERO.angleTo(Vec2d(-1, 0)).toDegrees(), `is`(270.0))
    }

    @Test
    fun angle315() {
        assertThat(ZERO.angleTo(Vec2d(-1, -1)).toDegrees(), `is`(315.0))
    }

}