package math.labs.we

import kotlin.math.pow

abstract class WEModelBase(
        var v : Double = 1.0,
        var h : Double = 0.1,
        @get:JvmName("gett") @set:JvmName("sett") var t : Double = 0.1,
        @get:JvmName("getT") @set:JvmName("setT") var T : Double = 100.0,
        var L : Double = 10.0
) {

    abstract fun f0(x: Double): Double
    abstract fun g(x: Double): Double
    abstract fun q1(x: Double): Double
    abstract fun q2(x: Double): Double

    fun solve(): List<List<Pair<Double, Double>>> {
        val result = mutableListOf<List<Pair<Double, Double>>>()
        val Nt = (T/t).toInt()
        val Nx = (L/h).toInt()
        val x = linSpace(0.0, L, Nx+1)
        val c = ((1/v) * t)/h
        val up = DoubleArray(Nx+1)
        var u = DoubleArray(Nx+1)
        var um = DoubleArray(Nx+1)
        for(i in 0..Nx){
            u[i] = f0(x[i])
        }
        result.add(x.asIterable().zip(u.asIterable()))
        for(i in 1 until Nx) {
            up[i] = u[i] + t * g(x[i])
        }
        up[0]=q1(0.0)
        up[Nx]=q2(Nx.toDouble())
        result.add(x.asIterable().zip(up.asIterable()))
        um = u.clone()
        u = up.clone()
        for(n in 1 until Nt){
            for(i in 1 until Nx){
                up[i] = -um[i] + 2 * u[i] + c.pow(2) * (u[i-1] - 2*u[i] + u[i+1])
            }
            up[0]=q1(0.0)
            up[Nx]=q2(Nx.toDouble())
            result.add(x.asIterable().zip(up.asIterable()))
            um = u.clone()
            u = up.clone()
        }
        return result
    }

    private fun linSpace(start: Double, stop: Double, num: Int) = Array(num) { start + it * ((stop - start) / (num - 1)) }
}