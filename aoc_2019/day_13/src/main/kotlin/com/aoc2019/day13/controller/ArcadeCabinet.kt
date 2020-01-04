package com.aoc2019.day13.controller

import com.aoc2019.common.computer.Computer
import com.aoc2019.common.math.Vec2i
import com.aoc2019.day13.model.tile.*

class ArcadeCabinet private constructor(
        val computer: Computer
) {

    fun setCredits(amount: Int) {
        computer.memory[0] = amount.toLong()
    }

    fun iterate() {
        computer.iterate()
    }

    fun run() {
        computer.iterateUntilFinishedOrWaiting()
    }

    fun getTiles(): List<Tile> = computer.outputs
            .chunked(3)
            .mapNotNull {
                val pos = Vec2i(it[0].toInt(), it[1].toInt())
                when (it[2].toInt()) {
                    0 -> EmptyTile(pos)
                    1 -> WallTile(pos)
                    2 -> BlockTile(pos)
                    3 -> PaddleTile(pos)
                    4 -> BallTile(pos)
                    else -> null
                }
            }

    fun getBlockCount(): Int = getTiles().count { it is BlockTile }

    companion object {

        fun from(input: String): ArcadeCabinet = ArcadeCabinet(Computer.from(input))

    }

}