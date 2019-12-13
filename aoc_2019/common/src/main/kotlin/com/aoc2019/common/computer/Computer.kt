package com.aoc2019.common.computer

class Computer(
        val program: Array<Int>,
        val inputValues: ArrayList<Int>
) {

    var programCounter = 0
    var finished = false
    var outputs = ArrayList<Int>()

    fun iterate() {
        if (finished) {
            return
        }

        val pc = programCounter
        when (program[pc].toString().takeLast(2).toInt()) {
            1 -> add()
            2 -> multiply()
            3 -> takeInput()
            4 -> writeOutput()
            99 -> halt()
            else -> throw IllegalStateException("Unknown opcode ${program[pc]} at index $pc")
        }
    }

    private fun getModeIndicators(expectedLength: Int) = program[programCounter]
            .toString()
            .dropLast(2)
            .reversed()
            .padEnd(expectedLength, '0')
            .map { it.toString().toInt() }
            .take(expectedLength)

    private fun getParamValue(
            modeIndicator: Int,
            pcOffset: Int
    ) = when (modeIndicator) {
        0 -> program[program[programCounter + pcOffset]]
        1 -> program[programCounter + pcOffset]
        else -> throw IllegalArgumentException("Unhandled mode: $modeIndicator")
    }

    private fun add() {
        val modeIndicators = getModeIndicators(2)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        program[program[programCounter + 3]] = paramValue1 + paramValue2

        programCounter += 4
    }

    private fun multiply() {
        val modeIndicators = getModeIndicators(2)

        val paramValue1 = getParamValue(modeIndicators[0], 1)
        val paramValue2 = getParamValue(modeIndicators[1], 2)

        program[program[programCounter + 3]] = paramValue1 * paramValue2

        programCounter += 4
    }

    fun takeInput() {
        program[program[programCounter + 1]] = inputValues.first()

        inputValues.removeAt(0) // "Consume" the value

        programCounter += 2
    }

    fun writeOutput() {

        val modeIndicators = getModeIndicators(1)

        val paramValue = getParamValue(modeIndicators[0], 1)

        outputs.add(paramValue)

        programCounter += 2
    }

    fun halt() {
        finished = true
    }

    fun iterateUntilFinished(): Computer {
        while(!finished) {
            iterate()
        }
        return this
    }

    companion object {
        fun from(
                programData: String,
                input: ArrayList<Int> = arrayListOf()
        ): Computer = Computer(programData
                .trim()
                .split(",")
                .map { it.trim().toInt() }
                .toTypedArray(),
                input
        )
    }

}