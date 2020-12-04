package com.aoc2019.day21

class And(
    firstArg: Char,
    secondArg: Char
): Instruction(firstArg, secondArg) {

    override fun getAscii(): String = "AND $firstArg $secondArg"

}