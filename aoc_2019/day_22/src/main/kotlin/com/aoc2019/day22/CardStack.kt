package com.aoc2019.day22

import kotlin.math.absoluteValue

data class CardStack(
    val cards: List<SpaceCard>
) {

    fun dealToNewStack(): CardStack = CardStack(
        cards.reversed()
    )

    fun cut(amount: Int): CardStack {
        return if (amount >= 0) {
            CardStack(
                cards.drop(amount) + cards.take(amount)
            )
        } else {
            CardStack(
                cards.takeLast(amount.absoluteValue) + cards.dropLast(amount.absoluteValue)
            )
        }
    }

    fun dealWithIncrement(amount: Int): CardStack {
        if (amount <= 0) {
            throw IllegalArgumentException("Amount must be > 0")
        }

        val cardArray = Array(cards.size) { SpaceCard(-1L) }

        cards.forEachIndexed { index, card ->
            cardArray[(index * amount) % cardArray.size] = card
        }

        return CardStack(cardArray.toList())
    }

    fun processInstructions(instructions: List<String>): CardStack {
        val stacks = mutableListOf(this)

        instructions.forEach { instruction ->
            val instructionSplit = instruction.split(" ")
            val last = stacks.last()

            stacks.add(
                when {
                    instruction == "deal into new stack" -> last.dealToNewStack()
                    instruction.startsWith("deal with increment") -> last.dealWithIncrement(
                        instructionSplit.last().toInt()
                    )
                    instruction.startsWith("cut") -> last.cut(instructionSplit.last().toInt())
                    else -> throw IllegalArgumentException("Unrecognised instruction: $instruction")
                }
            )
        }

        return stacks.last()
    }

}