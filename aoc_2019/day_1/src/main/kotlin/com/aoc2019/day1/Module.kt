package com.aoc2019.day1

import kotlin.math.floor
import kotlin.math.max

class Module(
        val mass: Number
) {

    private fun fuelRequirement(value: Double): Double = max(floor(value.toDouble() / 3) - 2, 0.0)

    fun baseFuelRequirement(): Double = fuelRequirement(mass.toDouble())

    fun totalFuelRequirement(): Double {
        val base = ArrayList<Double>(listOf(baseFuelRequirement()))

        while (base.last() != 0.0) {
            base += fuelRequirement(base.last())
        }

        return base.sum()
    }

}