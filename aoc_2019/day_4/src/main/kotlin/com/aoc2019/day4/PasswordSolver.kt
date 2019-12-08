package com.aoc2019.day4

class PasswordSolver(
        val lowerLimit: Int,
        val upperLimit: Int
) {

    fun isPasswordValid(digits: Array<Int>): Boolean {
        if (digits.size != 6) {
            return false
        }

        val value = digits.passwordValue()

        if (value < lowerLimit || value > upperLimit) {
            return false
        }

        if (!digits.toList().windowed(2).any { it[0] == it[1] }) {
            return false
        }

        if (!digits.sortedArray().contentEquals(digits)) {
            return false
        }

        return true
    }

    fun generateValidPasswords(): Int {
        val startString = lowerLimit.toString()
        val curr = arrayOf(
                startString[0].toString().toInt(),
                startString[1].toString().toInt(),
                startString[2].toString().toInt(),
                startString[3].toString().toInt(),
                startString[4].toString().toInt(),
                startString[5].toString().toInt()
        )

        var validPasswordCount = 0

        while (curr.passwordValue() <= upperLimit) {
            if (isPasswordValid(curr)) {
                validPasswordCount++
            }

            curr[5]++
            (5.downTo(0)).forEach { index ->
                if (curr[index] >= 10) {
                    curr[index] = 0

                    if (index > 0) {
                        curr[index - 1]++
                    }
                }
            }
        }

        return validPasswordCount
    }

}