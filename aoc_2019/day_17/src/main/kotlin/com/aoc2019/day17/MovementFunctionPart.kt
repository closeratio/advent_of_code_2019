package com.aoc2019.day17

data class MovementFunctionPart(
        val direction: Direction,
        val amount: Int
) {

    fun toShortString(): String = direction.shortened + amount

}

