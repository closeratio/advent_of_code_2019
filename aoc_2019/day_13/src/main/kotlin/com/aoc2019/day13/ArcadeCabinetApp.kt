package com.aoc2019.day13

import com.aoc2019.day13.controller.ArcadeCabinetController
import com.aoc2019.day13.model.ArcadeCabinetModel
import com.aoc2019.day13.model.ArcadeCabinetModelScope
import com.aoc2019.day13.view.MainWindow
import com.aoc2019.day13.view.Styles
import tornadofx.App
import tornadofx.find

class ArcadeCabinetApp: App(
        MainWindow::class,
        Styles::class,
        ArcadeCabinetModelScope(
                ArcadeCabinetModel()
        )
) {

    override fun stop() {
        super.stop()

        find<ArcadeCabinetController>().stop()
    }
}