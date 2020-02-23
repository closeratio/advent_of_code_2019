package com.aoc2019.day14

data class Reaction(
        val inputs: Set<ChemicalAmount>,
        val output: ChemicalAmount
) {

    fun getIterationsNeededToProduce(amountToManufacture: Long): Long {
        return when {
            amountToManufacture % output.amount == 0L -> amountToManufacture / output.amount
            else -> (amountToManufacture / output.amount) + 1
        }
    }

}