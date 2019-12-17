package com.aoc2019.day7

import com.aoc2019.common.computer.Computer

class Amplifier(
        val computer: Computer
) {
    fun computeOutput() {
        computer.iterateUntilFinished()
    }
}