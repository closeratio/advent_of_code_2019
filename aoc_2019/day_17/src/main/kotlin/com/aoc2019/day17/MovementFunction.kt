package com.aoc2019.day17

data class MovementFunction(
        val parts: List<MovementFunctionPart>
) {

    fun toCode(): List<Long> = parts
            .joinToString(",", postfix = "\n") { it.direction.shortened + "," + it.amount }
            .map { it.toLong() }

}