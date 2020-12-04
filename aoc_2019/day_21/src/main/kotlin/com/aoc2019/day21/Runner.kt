package com.aoc2019.day21

object Runner {

    fun part1() {
        val springScript = SpringScript.from(javaClass.getResource("/part_1_program.txt").readText())

        println(springScript.walk())
    }

    fun part2() {
        val springScript = SpringScript.from(javaClass.getResource("/part_2_program.txt").readText())

        println(springScript.run())
    }

    @JvmStatic
    fun main(arg: Array<String>) {
        part1()
        part2()
    }

}