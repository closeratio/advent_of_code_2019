package com.aoc2019.day14

class Chemical(
        val id: ChemicalId
) {

    private val creationReactions = mutableListOf<Reaction>()
    private val contributedReactions = mutableListOf<Reaction>()

    fun addCreationReaction(reaction: Reaction) = creationReactions.add(reaction)
    fun addContributedReaction(reaction: Reaction) = contributedReactions.add(reaction)

    fun getRequiredAmount(inputChemical: ChemicalId): Long {
        return if (inputChemical == id) {
            1
        } else {
            creationReactions
                    .flatMap { reaction -> reaction.inputs
                            .map { it.chemical.getRequiredAmount(inputChemical) * it.amount }}
                    .reduce { acc, curr -> acc + curr }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Chemical

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Chemical(id=${id.id})"
    }

}