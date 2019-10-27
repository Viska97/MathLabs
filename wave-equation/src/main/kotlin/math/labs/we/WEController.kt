package math.labs.we

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.chart.XYChart
import tornadofx.*
import java.util.concurrent.TimeUnit

class WEController : Controller() {

    enum class Models(val modelName : String)
    { StringModel("Колебания струны"), SinusModel("Синусоида") }

    val selectedModel = SimpleObjectProperty<Models>().apply {
        value = Models.StringModel
    }
    val x = FXCollections.observableArrayList<XYChart.Data<Number,Number>>()
    val startEnabled = SimpleBooleanProperty(true)
    val stopEnabled = SimpleBooleanProperty(false)

    private val disposable = CompositeDisposable()

    fun solve() {
        val model = when (selectedModel.value) {
            Models.SinusModel -> SinusModel(0.1, 0.1, 100.0, 20.0)
            Models.StringModel -> StringModel(0.1, 0.1, 100.0)
            else -> StringModel(0.1, 0.1, 100.0)
        }
        disposable.add(Observable.just(model.solve())
                .flatMapIterable { it }
                .map { it.map { item-> XYChart.Data<Number,Number>(item.first, item.second) } }
                .delayEach(50, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe {
                    x.clear()
                    x.addAll(it)
                })
        startEnabled.set(false)
        stopEnabled.set(true)
    }

    fun stop() {
        disposable.clear()
        x.clear()
        stopEnabled.set(false)
        startEnabled.set(true)
    }

    private fun <T> Observable<T>.delayEach(interval: Long, timeUnit: TimeUnit): Observable<T> =
            Observable.zip(
                    this,
                    Observable.interval(interval, timeUnit),
                    BiFunction { item, _ -> item }
            )
}