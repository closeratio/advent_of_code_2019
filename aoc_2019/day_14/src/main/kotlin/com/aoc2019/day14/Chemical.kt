package com.aoc2019.day14

class Chemical(
        val id: ChemicalId
) {

    private val creationReactions = mutableSetOf<Reaction>()
    private val contributedReactions = mutableSetOf<Reaction>()

    fun addCreationReaction(reaction: Reaction) {
        if (creationReactions.isNotEmpty()) {
            throw IllegalStateException("Only one reaction can create $id")
        }

        creationReactions.add(reaction)
    }

    fun addContributedReaction(reaction: Reaction) = contributedReactions.add(reaction)

    fun manufacture(
            amount: Long,
            inventory: MutableMap<ChemicalId, Long> = HashMap(),
            manufactured: MutableMap<ChemicalId, Long> = HashMap()
    ): Map<ChemicalId, Long> {
        // Populate maps to make the rest of this method a bit cleaner
        inventory.putIfAbsent(id, 0)
        manufactured.putIfAbsent(id, 0)

        if (id == ChemicalId("ORE")) {
            manufactured[id] = manufactured[id]!! + amount
            return manufactured
        }

        // Get how much of the chemical we already have available
        val amountInInventory = inventory.getValue(id)

        // Work out how much more we need to  make (if any)
        val manufactureRequirement = when {
            amountInInventory == 0L -> amount
            amountInInventory >= amount -> {
                inventory[id] = inventory.getValue(id) - amount
                0
            }
            else -> {
                val remaining = amount - inventory.getValue(id)
                inventory[id] = 0
                remaining
            }
        }

        // If there's still something left to manufacture, make it
        if (manufactureRequirement > 0) {
            val reaction = creationReactions.first()

            // Work out how many manufacturing iterations needed to produce required amount
            val requiredIterations: Long = reaction.getIterationsNeededToProduce(manufactureRequirement)
            val manufacturedCount = requiredIterations * reaction.output.amount

            // Update manufactured map with additional count
            manufactured[id] = manufactured.getValue(id) + manufacturedCount

            // Update inventory with spare amount after taking what was needed
            inventory[id] = inventory.getValue(id) + (manufacturedCount - manufactureRequirement)

            // Manufacture all upstream chemicals
            reaction.inputs.forEach { chemAmount ->
                chemAmount.chemical.manufacture(chemAmount.amount * requiredIterations, inventory, manufactured)
            }
        }

        return manufactured
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