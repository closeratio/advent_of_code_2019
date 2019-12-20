package com.aoc2019.day8

class Layer private constructor(
        val rows: List<List<Int>>,
        val width: Int,
        val height: Int
) {

    companion object {

        fun from(
                chunk: String,
                width: Int,
                height: Int
        ): Layer = Layer(
                chunk.chunked(width).map { it.map { it.toString().toInt() } },
                width,
                height
        )

    }

}