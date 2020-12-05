package com.aoc2019.day22

object Runner {

    private val cardStack = CardStack(
        (0L..10_006L).map { SpaceCard(it) }
    )

    fun part1() {

        val endStack = cardStack.processInstructions(
            javaClass.getResource("/input.txt")
                .readText()
                .trim()
                .split("\n")
                .map { it.trim() }
        )

        val result = endStack.cards
            .mapIndexedNotNull { index, spaceCard ->
                if (spaceCard.id == 2019L) {
                    index
                } else {
                    null
                }
            }
            .first()

        println("Card 2019 is at index $result")
    }

    fun part2() {

    }

    @JvmStatic
    fun main(arg: Array<String>) {
        part1()
        part2()
    }

}