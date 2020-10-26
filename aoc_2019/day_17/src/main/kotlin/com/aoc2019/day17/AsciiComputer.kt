package com.aoc2019.day17

import com.aoc2019.common.computer.Computer
import com.aoc2019.common.math.Vec2i
import com.aoc2019.day17.Direction.*

class AsciiComputer(
        val computer: Computer
) {

    fun execute(): WorldState = computer
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
                        '>' -> setOf(Scaffold(vec), Robot(vec, RIGHT))
                        '<' -> setOf(Scaffold(vec), Robot(vec, LEFT))
                        '^' -> setOf(Scaffold(vec), Robot(vec, UP))
                        'v' -> setOf(Scaffold(vec), Robot(vec, DOWN))
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

    companion object {
        fun from(
                input: String
        ): AsciiComputer = AsciiComputer(
                Computer.from(input)
        )
    }

}