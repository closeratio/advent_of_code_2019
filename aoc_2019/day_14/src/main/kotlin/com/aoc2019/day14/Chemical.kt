package com.aoc2019.day14

class Chemical(
        val id: ChemicalId
) {

    private val creationReactions = mutableListOf<Reaction>()
    private val contributedReactions = mutableListOf<Reaction>()

    fun addCreationReaction(reaction: Reaction) = creationReactions.add(reaction)
    fun addContributedReaction(reaction: Reaction) = contributedReactions.add(reaction)

    fun manufacture(
            amount: Long,
            inventory: MutableMap<ChemicalId, Long> = HashMap(),
            manufactured: MutableMap<ChemicalId, Long> = HashMap()
    ): Map<ChemicalId, Long> {
        // Populate maps to make the rest of this method a bit cleaner
        inventory.putIfAbsent(id, 0)
        manufactured.putIfAbsent(id, 0)

        // Get how much of the chemical we already have available
        val amountInInventory = inventory.getValue(id)

        // Work out how much more we need to  make (if any)
        val amountToManufacture = when {
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
        if (amountToManufacture > 0) {

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