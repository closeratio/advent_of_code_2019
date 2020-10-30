package com.aoc2019.day20

data class VisitedSpace(
        val space: OpenSpace,
        val steps: Int,
        val level: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VisitedSpace) return false

        if (space != other.space) return false
        if (level != other.level) return false

        return true
    }

    override fun hashCode(): Int {
        var result = space.hashCode()
        result = 31 * result + level
        return result
    }
}