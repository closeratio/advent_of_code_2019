package com.aoc2019.day13.controller

import com.aoc2019.day13.model.ArcadeCabinetModelScope
import com.aoc2019.day13.model.ArcadeCabinetStatus.FINISHED
import com.aoc2019.day13.model.ArcadeCabinetStatus.RUNNING
import com.aoc2019.day13.model.tile.BallTile
import com.aoc2019.day13.model.tile.BlockTile
import com.aoc2019.day13.model.tile.PaddleTile
import com.aoc2019.day13.model.tile.WallTile
import tornadofx.Controller
import tornadofx.runLater
import java.util.concurrent.Executors

class ArcadeCabinetController: Controller() {

    private val model = (super.scope as ArcadeCabinetModelScope).model

    private val arcadeCabinet = ArcadeCabinet.from(javaClass.getResource("/input.txt").readText())
    private val exec = Executors.newSingleThreadExecutor()

    fun start() {
        model.status = RUNNING

        exec.submit {
            try {
                arcadeCabinet.setCredits(2)

                while (!arcadeCabinet.computer.finished) {
                    arcadeCabinet.iterate()

                    // If the full "frame" has been drawn, update the state and pause
                    if (arcadeCabinet.computer.outputs.size == 967 * 3) {
                        // Update states
                        val tileMap = arcadeCabinet.getTiles().groupBy { it::class }
                        val wallTiles = tileMap.getValue(WallTile::class).map { it as WallTile }
                        val blockTiles = tileMap.getValue(BlockTile::class).map { it as BlockTile }
                        val paddle = tileMap.getValue(PaddleTile::class).first() as PaddleTile
                        val ball = tileMap.getValue(BallTile::class).first() as BallTile

                        // Clear outputs
                        arcadeCabinet.computer.outputs.clear()

                        runLater {
                            model.ballTile = ball
                            model.paddleTile = paddle
                            model.wallTiles.setAll(wallTiles)
                            model.blockTiles.setAll(blockTiles)
                            model.frameCount++
                        }

                        Thread.sleep(1000 / 30) // 30 FPS
                    }
                }
            } catch (exc: Exception) {
                exc.printStackTrace()
                runLater {
                    throw exc
                }
            }

            runLater {
                model.status = FINISHED
            }
        }
    }

    fun stop() {

    }

}