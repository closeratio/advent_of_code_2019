package com.aoc2019.day17

data class MovementRoutine(
        val functionCalls: List<String>
) {

    fun toCode(): List<Long> = functionCalls
            .joinToString(",", postfix = "\n")
            .map { it.toLong() }

}