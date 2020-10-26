package com.aoc2019.day17

enum class Orientation {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    fun left(): Orientation = when (this) {
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
    }

    fun right(): Orientation = when (this) {
        NORTH -> EAST
        WEST -> NORTH
        SOUTH -> WEST
        EAST -> SOUTH
    }
}