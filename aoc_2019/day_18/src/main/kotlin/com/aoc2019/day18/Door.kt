package com.aoc2019.day18

import com.aoc2019.common.math.Vec2i

class Door(
        val position: Vec2i,
        val id: String
): WorldObject() {

    init {
        assert(id.toUpperCase() == id)
    }

    fun worksWith(key: Key): Boolean = key.worksWith(this)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Door) return false

        if (position != other.position) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }

    override fun toString(): String {
        return "Door(position=$position, id='$id')"
    }


}