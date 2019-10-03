package math.labs.lvm

class LVModel {

    val victims = mutableListOf<Double>()
    val predators = mutableListOf<Double>()
    val times = mutableListOf<Double>()

    var N: Double = DEFAULT_N
    var P: Double = DEFAULT_P
    var a: Double = DEFAULT_A
    var b: Double = DEFAULT_B
    var c: Double = DEFAULT_C
    var d: Double = DEFAULT_D
    var h: Double = DEFAULT_H
    var t: Double = 0.0

    fun calculateNext() {
        N += ((a * N - b * N * P) * h)
        P += ((c * N * P - d * P) * h)
        if(N < 0) {
            N = 0.0
        }
        if(P < 0) {
            P = 0.0
        }
        t += h
        victims.add(N)
        predators.add(P)
        times.add(t)
    }

    fun clear() {
        victims.clear()
        predators.clear()
        times.clear()
        t = 0.0
        victims.add(N)
        predators.add(P)
        times.add(0.0)
    }

    companion object {
        const val DEFAULT_N = 10.0
        const val DEFAULT_P = 10.0
        const val DEFAULT_A = 0.1
        const val DEFAULT_B = 0.02
        const val DEFAULT_C = 0.02
        const val DEFAULT_D = 0.09
        const val DEFAULT_H = 1.0
    }
}