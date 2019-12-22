package com.aoc2019.common.computer

import com.aoc2019.common.computer.opcodes.*
import java.util.*
import kotlin.collections.LinkedHashMap

class Computer(
        val memory: Memory,
        val inputValues: LinkedList<Long>
) {

    var programCounter = 0L
    var relativeBase = 0L
    var finished = false
    var waiting = false
    var outputs = LinkedList<Long>()

    val opcodeMap = listOf(
            Add(),
            Multiply(),
            TakeInput(),
            WriteOutput(),
            JumpIfTrue(),
            JumpIfFalse(),
            LessThan(),
            EqualTo(),
            AdjustRelativeBase(),
            Halt()
    ).associateBy { it.getCode() }

    fun iterate() {
        if (finished) {
            return
        }

        val pc = programCounter
        val opcode = memory[pc].toString().takeLast(2).toInt()

        opcodeMap
                .getOrElse(opcode) { throw IllegalStateException("Unknown opcode $opcode at index $pc") }
                .execute(this)
    }

    fun iterateUntilFinishedOrWaiting(): Computer {
        waiting = false
        while (!finished && !waiting) {
            iterate()
        }
        return this
    }

    companion object {
        fun from(
                programData: String,
                input: List<Long> = listOf()
        ): Computer = Computer(Memory(LinkedHashMap(programData
                .trim()
                .split(",")
                .mapIndexed { index, value -> index.toLong() to value.trim().toLong() }
                .toMap())),
                LinkedList(input)
        )
    }

}