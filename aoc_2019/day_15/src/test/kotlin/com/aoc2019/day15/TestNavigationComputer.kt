package com.aoc2019.day15

import com.aoc2019.common.computer.Computer

class TestNavigationComputer {

    val computer = NavigationComputer(Computer.from(javaClass.getResource("/input.txt").readText()))

}