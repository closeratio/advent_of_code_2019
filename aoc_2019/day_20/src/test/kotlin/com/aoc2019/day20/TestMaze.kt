package com.aoc2019.day20

import com.aoc2019.common.math.Vec2i
import com.aoc2019.day20.PortalType.INNER
import com.aoc2019.day20.PortalType.OUTER
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsIterableWithSize.iterableWithSize
import org.hamcrest.collection.IsMapWithSize.aMapWithSize
import org.hamcrest.core.IsIterableContaining.hasItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestMaze {

    @Test
    fun fromInput1() {
        val maze = Maze.from(javaClass.getResource("/test_input_1.txt").readText())

        assertThat(maze.openSpaces, aMapWithSize(47))
        assertThat(maze.walls, aMapWithSize(173))
        assertThat(maze.portals, iterableWithSize(6))

        assertThat(maze.portals, hasItem(Portal("BC", OpenSpace(Vec2i(2, 8)), OUTER)))
    }

    @Test
    fun fromInput2() {
        val maze = Maze.from(javaClass.getResource("/test_input_2.txt").readText())

        assertThat(maze.openSpaces, aMapWithSize(313))
        assertThat(maze.walls, aMapWithSize(387))
        assertThat(maze.portals, iterableWithSize(20))

        assertThat(maze.portals, hasItem(Portal("AS", OpenSpace(Vec2i(17, 8)), INNER)))
    }

    @Test
    fun calculateShortestRouteInput1() {
        val maze = Maze.from(javaClass.getResource("/test_input_1.txt").readText())
        val result = maze.calculateShortestRoute()
        assertThat(result, `is`(23))
    }

    @Test
    fun calculateShortestRouteInput2() {
        val maze = Maze.from(javaClass.getResource("/test_input_2.txt").readText())
        val result = maze.calculateShortestRoute()
        assertThat(result, `is`(58))
    }

    @Test
    fun calculateShortestRouteRecursiveInput1() {
        val maze = Maze.from(javaClass.getResource("/test_input_1.txt").readText())
        val result = maze.calculateShortestRoute(true)
        assertThat(result, `is`(26))
    }

    @Test
    fun calculateShortestRouteRecursiveInput2() {
        val maze = Maze.from(javaClass.getResource("/test_input_2.txt").readText())
        Assertions.assertThrows(IllegalStateException::class.java) {
            maze.calculateShortestRoute(true)
        }
    }

    @Test
    fun calculateShortestRouteRecursiveInput3() {
        val maze = Maze.from(javaClass.getResource("/test_input_3.txt").readText())
        val result = maze.calculateShortestRoute(true)
        assertThat(result, `is`(396))
    }

}