package com.aoc2019.day11

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    fun left(): Direction = when(this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }

    fun right(): Direction = when(this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
}