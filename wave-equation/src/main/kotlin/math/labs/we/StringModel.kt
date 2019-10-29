package math.labs.we

import kotlin.math.pow

class StringModel(h: Double, k: Double, T: Double): WEModelBase(1.0, h, k, T, Math.PI) {

    override fun f(x: Double): Double = x.pow(2) * (Math.PI - x)
}