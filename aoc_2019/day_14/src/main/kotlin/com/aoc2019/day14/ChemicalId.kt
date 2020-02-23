package com.aoc2019.day14

data class ChemicalId(
        val id: String
) {

    companion object {
        val ORE = ChemicalId("ORE")
        val FUEL = ChemicalId("FUEL")
    }

}