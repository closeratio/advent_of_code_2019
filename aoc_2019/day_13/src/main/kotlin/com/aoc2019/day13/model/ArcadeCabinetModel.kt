package com.aoc2019.day13.model

import com.aoc2019.day13.model.ArcadeCabinetStatus.READY
import com.aoc2019.day13.model.tile.BallTile
import com.aoc2019.day13.model.tile.BlockTile
import com.aoc2019.day13.model.tile.PaddleTile
import com.aoc2019.day13.model.tile.WallTile
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import tornadofx.getValue
import tornadofx.setValue

class ArcadeCabinetModel {

    val statusProperty = SimpleObjectProperty<ArcadeCabinetStatus>(READY)
    var status by statusProperty

    val wallTiles = FXCollections.observableArrayList<WallTile>()
    val blockTiles = FXCollections.observableArrayList<BlockTile>()

    val paddleTileProperty = SimpleObjectProperty<PaddleTile>()
    var paddleTile by paddleTileProperty

    val ballTileProperty = SimpleObjectProperty<BallTile>()
    var ballTile by ballTileProperty

    val scoreProperty = SimpleLongProperty()
    var score by scoreProperty

    val frameCountProperty = SimpleLongProperty()
    var frameCount by frameCountProperty

    val sleepTimeProperty = SimpleIntegerProperty(1) // Default is 1000 FPS
    var sleepTime by sleepTimeProperty

}