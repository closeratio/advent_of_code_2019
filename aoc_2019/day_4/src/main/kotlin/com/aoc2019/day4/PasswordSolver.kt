package com.aoc2019.day4

import com.aoc2019.day4.PasswordSolver.PasswordValidationStrategy.ANY_DOUBLES
import com.aoc2019.day4.PasswordSolver.PasswordValidationStrategy.STRICT_DOUBLES

class PasswordSolver(
        val lowerLimit: Int,
        val upperLimit: Int
) {

    fun isPasswordValid(
            digits: Array<Int>,
            strategy: PasswordValidationStrategy
    ): Boolean {
        if (digits.size != 6) {
            return false
        }

        val value = digits.passwordValue()

        if (value < lowerLimit || value > upperLimit) {
            return false
        }

        if (when (strategy) {
                    ANY_DOUBLES -> !digits.toList().windowed(2).any { it[0] == it[1] }
                    STRICT_DOUBLES -> !hasValidDouble(digits)
                }) {
            return false
        }

        if (!digits.sortedArray().contentEquals(digits)) {
            return false
        }

        return true
    }

    private fun hasValidDouble(digits: Array<Int>): Boolean {
        var currIndex = 0

        while (currIndex < digits.size) {
            var windowEnd = currIndex + 1

            val currVal = digits[currIndex]
            while (windowEnd < digits.size && digits[windowEnd] == currVal) {
                windowEnd++
            }

            if (windowEnd == currIndex + 2) {
                return true
            }

            currIndex = windowEnd
        }

        return false
    }

    fun generateValidPasswords(strategy: PasswordValidationStrategy): Int {
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
            if (isPasswordValid(curr, strategy)) {
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

    enum class PasswordValidationStrategy() {
        ANY_DOUBLES,
        STRICT_DOUBLES
    }

}