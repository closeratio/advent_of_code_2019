package com.aoc2019.day12

import com.aoc2019.common.math.Vec3i
import kotlin.math.abs

class MoonSimulation(
        initialState: Set<MoonState>
) {

    private val states = mutableListOf(initialState)

    fun initialState() = states.first()
    fun lastState() = states.last()

    fun calculateNextState() {
        val lastState = states.last()
        states.add(lastState
                .map { moon ->
                    val velocity = moon.velocity + lastState
                            .filter { moon.name != it.name }
                            .map { moon.calculateVelocityDelta(it) }
                            .reduce { acc, curr -> acc + curr }

                    val position = moon.position + velocity

                    MoonState(
                            moon.name,
                            position,
                            velocity
                    )
                }
                .toSet())
    }

    fun calculateSystemEnergy(): Int {
        return lastState()
                .map { moonState ->
                    val pos = moonState.position
                    val vel = moonState.velocity
                    val potentialEnergy = abs(pos.x) + abs(pos.y) + abs(pos.z)
                    val kineticEnergy = abs(vel.x) + abs(vel.y) + abs(vel.z)

                    potentialEnergy * kineticEnergy
                }
                .sum()
    }

    companion object {
        private val INITIAL_STATE_REGEX = """x=(-?\d+), y=(-?\d+), z=(-?\d+)""".toRegex()
        private val FULL_STATE_REGEX = """pos=<x=\s*(-?\d+), y=\s*(-?\d+), z=\s*(-?\d+)>, vel=<x=\s*(-?\d+), y=\s*(-?\d+), z=\s*(-?\d+)>""".toRegex()

        private fun getMoonName(index: Int): String = when (index) {
            0 -> "Io"
            1 -> "Europa"
            2 -> "Ganymede"
            3 -> "Callisto"
            else -> throw IllegalArgumentException("Unknown moon with index $index")
        }

        fun from(input: String): MoonSimulation {
            return MoonSimulation(input
                    .trim()
                    .lines()
                    .map { it.trim() }
                    .mapIndexed { index, line ->
                        val name = getMoonName(index)

                        val (x, y, z) = INITIAL_STATE_REGEX
                                .find(line)!!
                                .groupValues
                                .drop(1)
                                .map { it.toInt() }

                        MoonState(
                                name,
                                Vec3i(x, y, z),
                                Vec3i.ZERO
                        )
                    }
                    .toSet())
        }

        fun fromState(input: String): Set<MoonState> {
            return input
                    .trim()
                    .lines()
                    .map { it.trim() }
                    .mapIndexed { index, line ->
                        val name = getMoonName(index)

                        val values = FULL_STATE_REGEX
                                .find(line)!!
                                .groupValues
                                .drop(1)
                                .map { it.toInt() }

                        MoonState(
                                name,
                                Vec3i(values[0], values[1], values[2]),
                                Vec3i(values[3], values[4], values[5])
                        )
                    }
                    .toSet()
        }

    }

}