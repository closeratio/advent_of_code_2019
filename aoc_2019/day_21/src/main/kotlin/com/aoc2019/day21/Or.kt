package com.aoc2019.day21

class Or(
    firstArg: Char,
    secondArg: Char
): Instruction(firstArg, secondArg) {

    override fun getAscii(): String = "OR $firstArg $secondArg"

}