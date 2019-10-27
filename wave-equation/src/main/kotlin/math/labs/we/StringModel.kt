package math.labs.we

import kotlin.math.pow

class StringModel(h: Double, t: Double, T: Double): WEModelBase(1.0, h, t, T, Math.PI) {

    override fun f0(x: Double): Double = x.pow(2) * (Math.PI - x)

    override fun g(x: Double): Double = 0.0

    override fun q1(x: Double): Double = 0.0

    override fun q2(x: Double): Double = 0.0
}