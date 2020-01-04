package com.aoc2019.day13

import com.aoc2019.day13.controller.ArcadeCabinet

object Runner {

    fun runPart1() {
        val cab = ArcadeCabinet.from(javaClass.getResource("/input.txt").readText())
        cab.run()

        val blockCount: Int = cab.getBlockCount()

        println(blockCount)
    }

}

fun main() {
    Runner.runPart1()
}