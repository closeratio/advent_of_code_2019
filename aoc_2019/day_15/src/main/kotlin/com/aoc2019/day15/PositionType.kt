package com.aoc2019.day15

enum class PositionType(val code: Long) {
    WALL(0),
    OPEN_SPACE(1),
    OXYGEN_SYSTEM(2),
    UNKNOWN(-1)
}