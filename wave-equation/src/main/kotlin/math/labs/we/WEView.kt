package math.labs.we

import javafx.scene.chart.NumberAxis
import javafx.scene.layout.VBox
import tornadofx.*

class WEView : View() {

    private val controller: WEController by inject()
    override val root = VBox()

    init {
        with(root){
            linechart("Волновое уравнение", NumberAxis(), NumberAxis(-15.0, 15.0, 0.5)) {
                series("u(x, t)", controller.x)
                createSymbols = false
                animated = false
            }
            togglegroup {
                WEController.Models.values().forEach {
                    radiobutton(it.modelName, value = it)
                }
                bind(controller.selectedModel)
            }
            hbox {
                button("Начать") {
                    action { controller.solve() }
                    disableProperty().bind(controller.startEnabled.not())
                }
                button("Остановить") {
                    action { controller.stop() }
                    disableProperty().bind(controller.stopEnabled.not())
                }
            }
        }
    }

}