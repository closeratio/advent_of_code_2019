package com.aoc2019.day21

import com.aoc2019.common.computer.Computer

data class SpringScript(
    val instructions: List<Instruction>
) {

    fun valid() = instructions.size in 1..15

    fun getInputs(finalInstruction: String): List<Long> {
        if (!valid()) {
            throw IllegalStateException("This springscript is not valid: $instructions")
        }

        return instructions
            .joinToString("\n", postfix = "\n$finalInstruction\n") { it.getAscii() }
            .map { it.toLong() }
    }

    private fun execute(finalInstruction: String): String {
        val computer = Computer.from(
            javaClass.getResource("/input.txt").readText(),
            getInputs(finalInstruction)
        )

        computer.iterateUntilFinishedOrWaiting()

        return computer.outputs.joinToString("") {
            if (it < 200) {
                it.toChar().toString()
            } else {
                it.toString()
            }
        }
    }

    fun walk(): String = execute("WALK")
    fun run(): String = execute("RUN")

    companion object {

        fun from(data: String): SpringScript = data
            .trim()
            .split("\n")
            .map { it.trim() }
            .map {
                val (operation, firstArg, secondArg) = it.split(" ")
                when (operation) {
                    "AND" -> And(firstArg.first(), secondArg.first())
                    "OR" -> Or(firstArg.first(), secondArg.first())
                    "NOT" -> Not(firstArg.first(), secondArg.first())
                    else -> throw IllegalArgumentException("Unrecognised operation: $operation")
                }
            }
            .let {
                SpringScript(it)
            }


    }

}