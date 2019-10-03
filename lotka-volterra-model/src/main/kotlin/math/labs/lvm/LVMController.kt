package math.labs.lvm

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import math.labs.lvm.LVModel.Companion.DEFAULT_A
import math.labs.lvm.LVModel.Companion.DEFAULT_B
import math.labs.lvm.LVModel.Companion.DEFAULT_C
import math.labs.lvm.LVModel.Companion.DEFAULT_D
import math.labs.lvm.LVModel.Companion.DEFAULT_H
import math.labs.lvm.LVModel.Companion.DEFAULT_N
import math.labs.lvm.LVModel.Companion.DEFAULT_P
import scientifik.plotly.PlotGrid
import scientifik.plotly.Plotly
import scientifik.plotly.makeFile
import scientifik.plotly.makeHtml
import tornadofx.*
import java.io.File

class LVMController : Controller() {

    val plotContent = SimpleStringProperty()
    val buttonEnabled = SimpleBooleanProperty(true)

    val N = SimpleStringProperty(DEFAULT_N.toPlainString())
    val P = SimpleStringProperty(DEFAULT_P.toPlainString())
    val a = SimpleStringProperty(DEFAULT_A.toPlainString())
    val b = SimpleStringProperty(DEFAULT_B.toPlainString())
    val c = SimpleStringProperty(DEFAULT_C.toPlainString())
    val d = SimpleStringProperty(DEFAULT_D.toPlainString())
    val h = SimpleStringProperty(DEFAULT_H.toPlainString())
    val T = SimpleStringProperty(DEFAULT_T.toPlainString())

    private val model = LVModel()
    private val modelFile = File(".", "lvm.html").apply {
        deleteOnExit()
    }

    fun calculate() {
        buttonEnabled.set(false)
        runAsync {
            applyValues()
            val T = T.get().toDouble()
            while(model.t < T) {
                model.calculateNext()
            }
            val plot = createPlot()
            val content = plot.makeHtml()
            plot.makeFile(modelFile, false)
            ui{
                plotContent.set(content)
                buttonEnabled.set(true)
            }
        }
    }

    fun reset() {
        N.set(DEFAULT_N.toPlainString())
        P.set(DEFAULT_P.toPlainString())
        a.set(DEFAULT_A.toPlainString())
        b.set(DEFAULT_B.toPlainString())
        c.set(DEFAULT_C.toPlainString())
        d.set(DEFAULT_D.toPlainString())
        h.set(DEFAULT_H.toPlainString())
        T.set(DEFAULT_T.toPlainString())
    }

    private fun applyValues() {
        model.N = N.value.toDouble()
        model.P = P.value.toDouble()
        model.a = a.value.toDouble()
        model.b = b.value.toDouble()
        model.c = c.value.toDouble()
        model.d = d.value.toDouble()
        model.h = h.value.toDouble()
        model.clear()
    }

    private fun createPlot(): PlotGrid = Plotly.page {
        plot(1,8){
            trace(model.times.toDoubleArray(), model.victims.toDoubleArray()) {
                name = "N (жертвы)"
            }
            trace(model.times.toDoubleArray(), model.predators.toDoubleArray()) {
                name = "P (хищники)"
            }
            layout {
                title = "Динамика популяций"
                xaxis { title = "Время" }
                yaxis { title = "Популяция" }
            }
        }
        plot(1,4){
            trace(model.victims.toDoubleArray(), model.predators.toDoubleArray()) {}
            layout {
                title = "Фазовый портрет"
                xaxis { title = "Хищник" }
                yaxis { title = "Жертва" }
            }
        }
    }

    private fun String.toDouble(): Double = java.lang.Double.parseDouble(this)
    private fun Double.toPlainString(): String = this.toString().toBigDecimal().toPlainString()

    companion object {
        const val DEFAULT_T = 1000.0
    }

}