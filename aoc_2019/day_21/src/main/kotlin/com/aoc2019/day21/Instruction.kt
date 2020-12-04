package com.aoc2019.day21

abstract class Instruction(
    val firstArg: Char,
    val secondArg: Char
) {

    abstract fun getAscii(): String

    override fun toString(): String {
        return getAscii()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Instruction) return false
        if (other.javaClass != javaClass) return false

        if (firstArg != other.firstArg) return false
        if (secondArg != other.secondArg) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstArg.hashCode()
        result = 31 * result + secondArg.hashCode()
        return result
    }


}