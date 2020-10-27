package com.aoc2019.day19

import com.aoc2019.common.computer.Computer

class TractorBeam(
        val input: String
) {

    fun computeAffectedPoints(): Long {
        val results = (0L..49L).flatMap { y ->
            (0L..49L).map { x ->
                inBeam(x, y)
            }
        }

        return results
                .filter { it }
                .size
                .toLong()
    }

    fun findClosestFittingPoint(): Long {
        var x = 0L
        var y = 0L

        while (true) {
            while (!inBeam(x, y + 99)) {
                x++
            }

            if (inBeam(x + 99, y)) {
                return (x * 10_000 + y)
            }

            y++
        }
    }

    private fun inBeam(x: Long, y: Long): Boolean = Computer
            .from(input, listOf(x, y))
            .iterateUntilFinishedOrWaiting()
            .outputs
            .first == 1L

}