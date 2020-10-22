package com.aoc2019.day18

class Maze(
        val walls: Set<Wall>
) {

    val wallMap = walls.associateBy { it.position }

}