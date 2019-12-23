package com.aoc2019.day11

import com.aoc2019.common.math.Vec2i
import com.aoc2019.day11.Colour.BLACK
import com.aoc2019.day11.Colour.WHITE

class Hull {

    private val colourMap = HashMap<Vec2i, Colour>()

    fun getColour(pos: Vec2i): Colour = colourMap.getOrPut(pos) { BLACK }

    fun setColour(pos: Vec2i, colour: Colour) {
        colourMap[pos] = colour
    }

    fun getPaintedPanelCount(): Int = colourMap.size

    fun render(): String {
        val minX = colourMap.keys.map { it.x }.min()!!
        val maxX = colourMap.keys.map { it.x }.max()!!
        val minY = colourMap.keys.map { it.y }.min()!!
        val maxY = colourMap.keys.map { it.y }.max()!!

        return (minY..maxY).map { y ->
            (minX..maxX).map { x ->
                when (colourMap.getOrDefault(Vec2i(x, y), BLACK)) {
                    BLACK -> " "
                    WHITE -> "#"
                }
            }.joinToString("")
        }.joinToString("\n")
    }

}