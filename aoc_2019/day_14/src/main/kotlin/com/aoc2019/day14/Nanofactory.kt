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
