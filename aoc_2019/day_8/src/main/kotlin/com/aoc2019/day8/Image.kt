package com.aoc2019.day8

class Image private constructor(
        val layers: List<Layer>,
        val width: Int,
        val height: Int
) {

    fun checksum(): Int {
        val targetLayer = layers.minBy { it.rows.flatMap { it }.filter { it == 0 }.size }!!
        val pixels = targetLayer.rows.flatMap { it }
        return pixels.filter { it == 1 }.size * pixels.filter { it == 2 }.size
    }

    fun render(): List<String> {
        return 0.until(height).map { y ->
            0.until(width).map { x ->
                val pixels = layers.map { it.rows[y][x] }
                val pixel = pixels.first { it == 0 || it == 1 }
                pixel.toString()
            }.joinToString("")
        }
    }


    companion object {
        fun from(
                input: String,
                width: Int,
                height: Int
        ) = Image(
                input.chunked(width * height).map { Layer.from(it, width, height) },
                width,
                height
        )
    }

}

