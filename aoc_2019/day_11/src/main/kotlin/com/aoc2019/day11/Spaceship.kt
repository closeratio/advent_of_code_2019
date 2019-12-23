package com.aoc2019.day11

import com.aoc2019.common.computer.Computer

class Spaceship private constructor(
        val hull: Hull,
        val robot: Robot
) {

    fun paintHull(initialColour: Colour) {
        robot.runUntilFinished(initialColour)
    }

    companion object {
        fun from(input: String): Spaceship {
            val hull = Hull()

            return Spaceship(
                    hull,
                    Robot(
                            Computer.from(input),
                            { hull.getColour(it) }
                     ) { pos, colour -> hull.setColour(pos, colour) }
            )
        }

    }

}