package com.aoc2019.day11

import com.aoc2019.common.computer.Computer
import com.aoc2019.common.math.Vec2i
import com.aoc2019.day11.Colour.BLACK
import com.aoc2019.day11.Colour.WHITE
import com.aoc2019.day11.Direction.*

class Robot(
        private val computer: Computer,
        val getColour: (pos: Vec2i) -> Colour,
        val setColour: (pos: Vec2i, colour: Colour) -> Unit
) {
    private var position: Vec2i = Vec2i.ZERO
    private var direction: Direction = NORTH

    fun runUntilFinished(initialColour: Colour) {
        computer.inputValues.push(initialColour.code.toLong())

        // Run until halted
        while (!computer.finished) {
            computer.iterateUntilFinishedOrWaiting()

            // Get outputs
            val (colourCode, actionCode) = computer.outputs
            computer.outputs.clear()

            // Set colour
            setColour(position, when (colourCode.toInt()) {
                0 -> BLACK
                1 -> WHITE
                else -> throw IllegalStateException("Unhandled colour code: $colourCode")
            })

            // Rotate
            direction = when(actionCode.toInt()) {
                0 -> direction.left()
                1 -> direction.right()
                else -> throw IllegalStateException("Unhandled action code: $actionCode")
            }

            // Move
            position = when (direction) {
                NORTH -> position.up()
                EAST -> position.right()
                SOUTH -> position.down()
                WEST -> position.left()
            }

            // If not finished, get input
            if (!computer.finished) {
                computer.inputValues.push(getColour(position).code.toLong())
            }
        }
    }
}