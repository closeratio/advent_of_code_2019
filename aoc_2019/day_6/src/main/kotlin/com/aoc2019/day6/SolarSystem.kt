package com.aoc2019.day6

class SolarSystem(
        val planets: Set<Planet>
) {

    fun totalOrbits(): Int {
        val roots = planets.filter { it.parent == null }
        if (roots.size != 1) {
            throw IllegalStateException("There can only be one root, there are currently " + roots.size)
        }

        val root = roots.first()

        return root.getTotalOrbits()
    }

    fun transfersToSanta(): Int {
        return 0
    }

    companion object {
        fun from(input: String): SolarSystem {
            return SolarSystem(input
                    .trim()
                    .split("\n")
                    .map { it.trim() }
                    .fold(HashMap<PlanetID, Planet>()) { planets, line ->
                        val (left, right) = line.split(")")
                        val leftID = PlanetID(left)
                        val rightID = PlanetID(right)

                        val leftPlanet = planets.getOrPut(leftID) { Planet(leftID, null, HashSet()) }
                        val rightPlanet = planets.getOrPut(rightID) { Planet(rightID, null, HashSet()) }

                        leftPlanet.children.add(rightPlanet)
                        rightPlanet.parent = leftPlanet

                        planets
                    }
                    .values
                    .toSet())
        }
    }

}