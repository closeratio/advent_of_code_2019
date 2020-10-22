package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i
import com.aoc2019.common.math.Vec2i.Companion.ZERO
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.core.IsIterableContaining.hasItem
import org.junit.jupiter.api.Test

class TestWorldState {

    @Test
    fun parse() {
        val ws = WorldState.parse(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(ws.player, `is`(Player(Vec2i(15, 1), setOf(), 0)))

        assertThat(ws.keys, hasSize(6))
        assertThat(ws.keys, hasItem(Key(Vec2i(7, 1), "e")))

        assertThat(ws.doors, hasSize(5))
        assertThat(ws.doors, hasItem(Door(Vec2i(9, 1), "C")))

        assertThat(ws.maze.walls, hasSize(75))
        assertThat(ws.maze.walls, hasItem(Wall(ZERO)))
    }

}