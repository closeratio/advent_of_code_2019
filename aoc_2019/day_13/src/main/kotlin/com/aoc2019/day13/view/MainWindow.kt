package com.aoc2019.day13.view

import com.aoc2019.day13.controller.ArcadeCabinetController
import com.aoc2019.day13.model.ArcadeCabinetModelScope
import com.aoc2019.day13.model.ArcadeCabinetStatus
import com.aoc2019.day13.model.ArcadeCabinetStatus.READY
import javafx.geometry.Pos.CENTER
import javafx.scene.layout.Priority.ALWAYS
import javafx.scene.paint.Color
import javafx.util.StringConverter
import tornadofx.*
import kotlin.Double.Companion.MAX_VALUE

class MainWindow : Fragment("Arcade Cabinet") {

    private val model = (super.scope as ArcadeCabinetModelScope).model
    private val controller = find<ArcadeCabinetController>()

    override val root = vbox(5) {
        padding = insets(5)

        val tileWidth = 20.0
        val canvWidth = 42.0 * tileWidth
        val canvHeight = 23.0 * tileWidth
        canvas(canvWidth, canvHeight) {

            // Redraw when the frame count is incremented
            model.frameCountProperty.addListener { _, _, _ ->
                val context = graphicsContext2D
                context.clearRect(0.0, 0.0, canvWidth, canvHeight)

                model.wallTiles.forEach {
                    context.fill = Color.BROWN
                    context.fillRect(
                            it.position.x.toDouble() * tileWidth,
                            it.position.y.toDouble() * tileWidth,
                            tileWidth,
                            tileWidth)
                }

                model.blockTiles.forEach {
                    context.fill = Color.BLUE
                    context.fillRect(
                            it.position.x.toDouble() * tileWidth,
                            it.position.y.toDouble() * tileWidth,
                            tileWidth,
                            tileWidth)
                }

                context.fill = Color.GREEN
                context.fillRect(
                        model.paddleTile.position.x.toDouble() * tileWidth,
                        model.paddleTile.position.y.toDouble() * tileWidth,
                        tileWidth,
                        tileWidth)

                context.fill = Color.RED
                context.fillRect(
                        model.ballTile.position.x.toDouble() * tileWidth,
                        model.ballTile.position.y.toDouble() * tileWidth,
                        tileWidth,
                        tileWidth)
            }
        }


        hbox(5) {
            alignment = CENTER
            label("Score:")
            textfield(model.scoreProperty) {
                isEditable = false
                hgrow = ALWAYS
                maxWidth = MAX_VALUE
            }
        }

        hbox(5) {
            alignment = CENTER
            label("Status:")
            textfield(model.statusProperty, object : StringConverter<ArcadeCabinetStatus>() {
                override fun toString(status: ArcadeCabinetStatus?): String = status?.name ?: ""
                override fun fromString(string: String?): ArcadeCabinetStatus = READY
            }) {
                isEditable = false
                hgrow = ALWAYS
                maxWidth = MAX_VALUE
            }
        }

        hbox(5) {
            alignment = CENTER
            label("Frame count:")
            textfield(model.frameCountProperty) {
                isEditable = false
                hgrow = ALWAYS
                maxWidth = MAX_VALUE
            }
        }

        hbox(5) {
            alignment = CENTER
            label("Frame sleep time:")
            spinner(min = 1, max = 1000, initialValue = 1, property = model.sleepTimeProperty) {
                hgrow = ALWAYS
                maxWidth = MAX_VALUE
            }
        }

        button("Start") {
            hgrow = ALWAYS
            maxWidth = MAX_VALUE
            disableProperty().bind(model.statusProperty.isNotEqualTo(READY))
            action {
                controller.start()
            }
        }
    }

}