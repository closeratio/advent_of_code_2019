package com.aoc2019.day14

class Nanofactory private constructor(
        val chemicals: Set<Chemical>,
        val reactions: Set<Reaction>
) {

    fun manufactureChemical(
            chemical: ChemicalId,
            amount: Long
    ): Map<ChemicalId, Long> {
        val output = chemicals.find { it.id == chemical }!!

        return output.manufacture(amount)
    }

    fun getMaxOutputForInput(
            inputAmount: ChemicalAmount,
            output: ChemicalId
    ): Long {
        // Establsh lower and upper
        var lower = 1L
        var upper = 1L
        var upperEstablished = false

        while (!upperEstablished) {
            val oreCount = manufactureChemical(output, upper).getValue(inputAmount.chemical.id)

            if (oreCount > inputAmount.amount) {
                upperEstablished =  true
            } else {
                lower = upper
                upper *= 2
            }
        }

        return findOutputAmount(
                inputAmount,
                output,
                lower,
                upper
        )
    }

    private fun findOutputAmount(
            inputAmount: ChemicalAmount,
            output: ChemicalId,
            lower: Long,
            upper: Long
    ): Long {
        return if (upper - lower < 10) { // A bit arbitrary, but probably quicker than doing more recursion
            lower.rangeTo(upper)
                    .map { it to manufactureChemical(output, it).getValue(inputAmount.chemical.id) }
                    .filter { it.second < inputAmount.amount }
                    .map { it.first }
                    .sorted()
                    .last()
        } else {
            val mid = lower + ((upper - lower) / 2)
            val oreCountMid = manufactureChemical(output, mid).getValue(inputAmount.chemical.id)

            if (oreCountMid >= inputAmount.amount) {
                findOutputAmount(inputAmount, output, lower, mid)
            } else {
                findOutputAmount(inputAmount, output, mid, upper)
            }
        }
    }

    companion object {

        private fun getChemicalAmount(
                input: String,
                chemicals: MutableMap<ChemicalId, Chemical>
        ): ChemicalAmount {
            val (amountString, idString) = input.split(" ")
            val id = ChemicalId(idString)
            return ChemicalAmount(chemicals.getOrPut(id, { Chemical(id) }), amountString.toLong())
        }

        fun from(input: String): Nanofactory {
            val chemicals = hashMapOf<ChemicalId, Chemical>()
            val reactions = input.trim()
                    .lines()
                    .map { it.trim() }
                    .map { it.split("=>") }
                    .map { (left, right) -> left.split(",").map { it.trim() } to right.trim() }
                    .map { (inputStrings, outputString) ->
                        val inputs = inputStrings.map { getChemicalAmount(it, chemicals) }
                        val output = getChemicalAmount(outputString, chemicals)

                        val reaction = Reaction(inputs.toSet(), output)

                        inputs.forEach { it.chemical.addContributedReaction(reaction) }
                        output.chemical.addCreationReaction(reaction)

                        reaction
                    }
                    .toSet()

            return Nanofactory(chemicals.values.toSet(), reactions)
        }

    }

}
