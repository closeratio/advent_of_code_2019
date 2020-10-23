package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i

data class Door(
        val position: Vec2i,
        val id: String
): WorldObject() {

    init {
        assert(id.toUpperCase() == id)
    }

    fun worksWith(key: Key): Boolean = key.worksWith(this)

}