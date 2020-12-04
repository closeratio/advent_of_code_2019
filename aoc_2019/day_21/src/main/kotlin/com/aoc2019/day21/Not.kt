package com.aoc2019.day21

class Not(
    firstArg: Char,
    secondArg: Char
): Instruction(firstArg, secondArg) {

    override fun getAscii(): String = "NOT $firstArg $secondArg"

}