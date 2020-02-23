package com.aoc2019.day14

object Runner {

    val nanofactory = Nanofactory.from(javaClass.getResource("/input.txt").readText())

    fun runPart1() {
        val manufactured = nanofactory.manufactureChemical(ChemicalId.FUEL, 1L)

        println(manufactured.getValue(ChemicalId.ORE))
    }

    fun runPart2() {
        val result = nanofactory.getMaxOutputForInput(
                ChemicalAmount(Chemical(ChemicalId.ORE), 1_000_000_000_000L),
                ChemicalId.FUEL)

        println(result)
    }

}

fun main() {
    Runner.runPart1()
    Runner.runPart2()
}