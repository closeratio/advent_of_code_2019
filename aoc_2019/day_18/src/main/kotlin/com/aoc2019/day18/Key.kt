package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i

data class Key(
        val position: Vec2i,
        val id: String
): WorldObject() {

    init {
        assert(id.toLowerCase() == id)
    }

    fun worksWith(door: Door): Boolean = door.id.toLowerCase() == id

}