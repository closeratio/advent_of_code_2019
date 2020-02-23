package com.aoc2019.day14

object Runner {

    fun runPart1() {
        val nanofactory = Nanofactory.from(javaClass.getResource("/input.txt").readText())
        val manufactured = nanofactory.manufactureChemical(ChemicalId("FUEL"), 1L)

        println(manufactured.getValue(ChemicalId("ORE")))
    }

}

fun main() {
    Runner.runPart1()
}