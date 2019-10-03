package math.labs.lvm

import javafx.scene.layout.VBox
import javafx.scene.text.Font
import tornadofx.*

class LVMView : View() {

    private val controller: LVMController by inject()
    override val root = VBox()

    private val webView = webview {
        setPrefSize(900.0, 500.0)
    }

    init {
        with(root) {
            text("Модель Лотки — Вольтерры") {
                font = Font(20.0)
            }
            form {
                hbox (20) {
                    vbox(20) {
                        fieldset("Начальные условия") {
                            field("N (популяция жертв)") {
                                textfield(controller.N){ filterInput { it.controlNewText.isDouble() } }
                            }
                            field("P (популяция хищников)") {
                                textfield(controller.P){ filterInput { it.controlNewText.isDouble() } }
                            }
                        }
                        button("Провести эксперимент") {
                            action { controller.calculate() }
                            disableProperty().bind(controller.buttonEnabled.not())
                        }
                    }
                    vbox(20) {
                        fieldset("Время") {
                            field("h (Δt)") {
                                textfield(controller.h){ filterInput { it.controlNewText.isDouble() } }
                            }
                            field("T (время эксперимента)") {
                                textfield(controller.T){ filterInput { it.controlNewText.isDouble() } }
                            }
                        }
                        button("Сбросить значения") { action { controller.reset() } }
                    }
                    fieldset("Коэффициенты") {
                        field("a") {
                            textfield(controller.a){ filterInput { it.controlNewText.isDouble() } }
                        }
                        field("b") {
                            textfield(controller.b){ filterInput { it.controlNewText.isDouble() } }
                        }
                        field("c") {
                            textfield(controller.c){ filterInput { it.controlNewText.isDouble() } }
                        }
                        field("d") {
                            textfield(controller.d){ filterInput { it.controlNewText.isDouble() } }
                        }
                    }
                }
                add(webView)
            }
        }
        controller.plotContent.addListener{ _, _, content ->
            webView.engine.loadContent(content, "text/html")
        }
    }

}