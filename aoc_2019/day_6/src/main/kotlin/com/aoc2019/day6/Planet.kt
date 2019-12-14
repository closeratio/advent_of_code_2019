package com.aoc2019.day6

class Planet(
        val id: PlanetID,
        var parent: Planet?,
        val children: MutableSet<Planet>
) {

    fun getParentOrbitCount(): Int = if (parent != null) 1 + parent!!.getParentOrbitCount() else 0

    fun getTotalOrbits(): Int {
        return getParentOrbitCount() + children.map { it.getTotalOrbits() }.sum()
    }

    fun parentPath(): List<Planet> = (parent?.parentPath() ?: listOf()) + this

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Planet

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}