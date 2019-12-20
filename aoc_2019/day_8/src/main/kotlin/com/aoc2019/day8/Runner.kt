package com.aoc2019.day8

object Runner {

    fun runPart1() {
        val image = Image.from(javaClass.getResource("/input.txt").readText(), 25, 6)

        val result: Int = image.checksum()
        println(result)
    }

    fun runPart2() {
        val image = Image.from(javaClass.getResource("/input.txt").readText(), 25, 6)

        val result: List<String> = image.render()
        println(result.joinToString("\n").replace("0", " "))
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}