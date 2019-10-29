package math.labs.we
import kotlin.math.sin

class SinusModel(h: Double, k: Double, T: Double, L: Double): WEModelBase(1.0, h, k, T, L) {

    override fun f(x: Double): Double = (L - x) * sin(x)

}