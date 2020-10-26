package com.aoc2019.day17

import com.aoc2019.common.computer.Computer
import com.aoc2019.common.math.Vec2i
import com.aoc2019.day17.Direction.LEFT
import com.aoc2019.day17.Direction.RIGHT
import com.aoc2019.day17.Orientation.*

class AsciiComputer(
        val computer: Computer
) {

    fun getScaffoldMap(): WorldState = computer
            .iterateUntilFinishedOrWaiting()
            .outputs
            .map { it.toChar() }
            .joinToString("")
            .trim()
            .split("\n")
            .flatMapIndexed { y, line ->
                println(line)
                line.flatMapIndexed { x, character ->
                    val vec = Vec2i(x, y)
                    when (character) {
                        '#' -> setOf(Scaffold(vec))
                        '>' -> setOf(Scaffold(vec), Robot(vec, EAST))
                        '<' -> setOf(Scaffold(vec), Robot(vec, WEST))
                        '^' -> setOf(Scaffold(vec), Robot(vec, NORTH))
                        'v' -> setOf(Scaffold(vec), Robot(vec, SOUTH))
                        else -> setOf()
                    }
                }
            }
            .let {
                WorldState(
                        it.filterIsInstance<Scaffold>().toSet(),
                        it.filterIsInstance<Robot>().first()
                )
            }

    fun traverseScaffold(): Long {
        // Wake up the robot
        wakeUp()

        // Setup the input data
        val functionA = MovementFunction(
                listOf(
                        MovementFunctionPart(RIGHT, 8),
                        MovementFunctionPart(LEFT, 12),
                        MovementFunctionPart(RIGHT, 8)
                )
        )

        val functionB = MovementFunction(
                listOf(
                        MovementFunctionPart(LEFT, 10),
                        MovementFunctionPart(LEFT, 10),
                        MovementFunctionPart(RIGHT, 8)
                )
        )

        val functionC = MovementFunction(
                listOf(
                        MovementFunctionPart(LEFT, 12),
                        MovementFunctionPart(LEFT, 12),
                        MovementFunctionPart(LEFT, 10),
                        MovementFunctionPart(RIGHT, 10)
                )
        )

        val movementRoutine = MovementRoutine(
                listOf("A", "A", "B", "C", "B", "C", "B", "A", "C", "A")
        )

        val input = movementRoutine.toCode() +
                functionA.toCode() +
                functionB.toCode() +
                functionC.toCode() +
                "n\n".map { it.toLong() }

        computer.inputValues.addAll(input)

        // Execute the program
        computer.iterateUntilFinishedOrWaiting()

        // Report the result
        return computer.outputs.last
    }

    private fun wakeUp() {
        computer.memory[0] = 2
    }

    companion object {
        fun from(
                input: String
        ): AsciiComputer = AsciiComputer(
                Computer.from(input)
        )
    }

}