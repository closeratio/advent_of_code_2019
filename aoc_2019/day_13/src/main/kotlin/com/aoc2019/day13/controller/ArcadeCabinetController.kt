package com.aoc2019.day13.controller

import com.aoc2019.day13.model.ArcadeCabinetModelScope
import com.aoc2019.day13.model.ArcadeCabinetStatus.FINISHED
import com.aoc2019.day13.model.ArcadeCabinetStatus.RUNNING
import com.aoc2019.day13.model.JoystickPosition.*
import com.aoc2019.day13.model.tile.*
import tornadofx.Controller
import tornadofx.runLater
import java.util.concurrent.Executors

class ArcadeCabinetController: Controller() {

    private val model = (super.scope as ArcadeCabinetModelScope).model

    private val arcadeCabinet = ArcadeCabinet.from(javaClass.getResource("/input.txt").readText())
    private val exec = Executors.newSingleThreadExecutor()

    private lateinit var ballTile: BallTile
    private lateinit var paddleTile: PaddleTile

    fun start() {
        model.status = RUNNING

        exec.submit {
            try {
                arcadeCabinet.setCredits(2)

                while (!arcadeCabinet.isFinished()) {
                    arcadeCabinet.run()

                    // Update states
                    val tileMap = arcadeCabinet.getTiles().groupBy { it::class }
                    val wallTiles = tileMap[WallTile::class]?.map { it as WallTile }
                    val blockTiles = tileMap[BlockTile::class]?.map { it as BlockTile }
                    val emptyTiles = tileMap[EmptyTile::class]?.map { it as EmptyTile }
                    val paddle = tileMap[PaddleTile::class]?.first() as PaddleTile?
                    val ball = tileMap[BallTile::class]?.first() as BallTile?
                    val score = (tileMap[ScoreTile::class]?.firstOrNull() as ScoreTile?)?.score

                    if (ball != null) {
                        ballTile = ball
                    }

                    if (paddle != null) {
                        paddleTile = paddle
                    }

                    // Clear outputs
                    arcadeCabinet.clearOutputs()

                    // Update model
                    runLater {
                        if (ball != null) {
                            model.ballTile = ball
                        }

                        if (paddle != null) {
                            model.paddleTile = paddle
                        }

                        if (wallTiles != null) {
                            model.wallTiles.setAll(wallTiles)
                        }

                        if (blockTiles != null) {
                            model.blockTiles.setAll(blockTiles)
                        } else if (emptyTiles != null) {
                            val emptyPositions = emptyTiles.map { it.position }.toSet()
                            model.blockTiles.removeIf { it.position in emptyPositions }
                        }

                        model.score = score ?: model.score

                        model.frameCount++
                    }

                    // Update joystick position (to autoplay)
                    val paddleXPos = paddleTile.position.x
                    val ballXPos = ballTile.position.x

                    arcadeCabinet.setJoystickPositon(when {
                        paddleXPos < ballXPos -> RIGHT
                        paddleXPos > ballXPos -> LEFT
                        else -> NEUTRAL
                    })

                    Thread.sleep(1) // 1000 FPS
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
        try {
            exec.shutdownNow()
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
    }

}