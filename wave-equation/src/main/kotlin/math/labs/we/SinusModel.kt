package math.labs.we
import kotlin.math.sin

class SinusModel(h: Double, t: Double, T: Double, L: Double): WEModelBase(1.0, h, t, T, L) {

    override fun f0(x: Double): Double = (L - x) * sin(x)

    override fun g(x: Double): Double = 0.0

    override fun q1(x: Double): Double = 0.0

    override fun q2(x: Double): Double = 0.0
}