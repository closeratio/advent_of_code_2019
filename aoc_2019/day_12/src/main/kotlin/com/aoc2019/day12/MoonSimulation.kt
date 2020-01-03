package com.aoc2019.day12

import com.aoc2019.common.math.Vec3i
import com.aoc2019.common.math.lowestCommonMultiplier
import kotlin.math.abs

class MoonSimulation(
        initialState: MoonStateSet
) {

    private val states = mutableListOf(initialState)

    fun initialState() = states.first()
    fun lastState() = states.last()

    fun calculateNextState() {
        val lastState = states.last().state
        states.add(MoonStateSet(lastState
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
                .toSet()))
    }

    fun calculateSystemEnergy(): Int {
        return lastState()
                .state
                .map { moonState ->
                    val pos = moonState.position
                    val vel = moonState.velocity
                    val potentialEnergy = abs(pos.x) + abs(pos.y) + abs(pos.z)
                    val kineticEnergy = abs(vel.x) + abs(vel.y) + abs(vel.z)

                    potentialEnergy * kineticEnergy
                }
                .sum()
    }

    fun findPeriod(): Long {
        var xPeriod: Int? = null
        var yPeriod: Int? = null
        var zPeriod: Int? = null

        val first = initialState()

        while (xPeriod == null || yPeriod == null || zPeriod == null) {
            calculateNextState()

            val last = lastState()

            if (xPeriod == null && last.equalDimension(first, Dimension.X)) {
                xPeriod = states.size - 1
            }

            if (yPeriod == null && last.equalDimension(first, Dimension.Y)) {
                yPeriod = states.size - 1
            }

            if (zPeriod == null && last.equalDimension(first, Dimension.Z)) {
                zPeriod = states.size - 1
            }
        }

        return listOf(xPeriod, yPeriod, zPeriod).lowestCommonMultiplier()
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
            return input
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
                    .toSet()
                    .let {
                        MoonSimulation(MoonStateSet(it))
                    }
        }

        fun fromState(input: String): MoonStateSet {
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
                    .let {
                        MoonStateSet(it)
                    }
        }

    }

}
