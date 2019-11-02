package math.labs.solarsystem

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector3
import kotlin.math.pow
import kotlin.math.sqrt

class SolarSystem {

    val bodies = mutableListOf<CelestialBody>()

    init {
        bodies.add(CelestialBody("Солнце", Color.YELLOW, 19.89F, 10.0F, Vector3(0.0F, 0.0F, 0.0F)))
        bodies.add(CelestialBody("Меркурий", Color.CORAL, 0.00000330F, 1.0F, Vector3(5.7F, 0.0F, 0.0F), v = Vector3(0.0F, 0.04736F, 0.0F)))
        bodies.add(CelestialBody("Венера", Color.CYAN, 0.0000487F, 2.0F, Vector3(10.8F, 0.0F, 0.0F), v = Vector3(0.0F, 0.03502F, 0.0F)))
        bodies.add(CelestialBody("Земля", Color.GREEN, 0.00005974F, 2.0F, Vector3(15.2F, 0.0F, 0.0F), v = Vector3(0.0F, 0.02929F, 0.0F)))
        bodies.add(CelestialBody("Марс", Color.RED, 0.00000642F, 3.0F, Vector3(22.8F, 0.0F, 0.0F), v = Vector3(0.0F, 0.02413F, 0.0F)))
        bodies.add(CelestialBody("Юпитер", Color.CORAL, 0.01899F, 5.0F, Vector3(77.8F, 0.0F, 0.0F), v = Vector3(0.0F, 0.01307F, 0.0F)))
        bodies.add(CelestialBody("Сатурн", Color.YELLOW, 0.00568F, 4.0F, Vector3(142.4F, 0.0F, 0.0F), v = Vector3(0.0F, 0.00969F, 0.0F)))
        bodies.add(CelestialBody("Уран", Color.BLUE, 0.000866F, 3.0F, Vector3(257.2F, 0.0F, 0.0F), v = Vector3(0.0F, 0.00681F, 0.0F)))
        bodies.add(CelestialBody("Нептун", Color.BLUE, 0.00103F, 10.0F, Vector3(449.9F, 0.0F, 0.0F), v = Vector3(0.0F, 0.00543F, 0.0F)))
    }

    fun compute(h: Float, count: Int) {
        for(i in 0 .. count) {
            computeAccelerations()
            computePositions(h)
            computeVelocities()
        }
    }

    private fun computeAccelerations() {
        for (i in bodies) {
            i.a = origin
            for (j in bodies) {
                if (i.name != j.name) {
                    i.a += (j.position - i.position) * (G * j.mass / (i.position - j.position).mod.pow(3))
                }
            }
        }
    }

    private fun computeVelocities() {
        for (i in bodies) i.v += i.a
    }

    private fun computePositions(h: Float) {
        for (i in bodies) {
            i.position += i.v + i.a * h
        }
    }

    operator fun Vector3.plus(v: Vector3) = Vector3(x + v.x, y + v.y, z + v.z)
    operator fun Vector3.minus(v: Vector3) = Vector3(x - v.x, y - v.y, z - v.z)
    operator fun Vector3.times(s: Float) = Vector3(s * x, s * y, s * z)

    val Vector3.mod get() = sqrt(x * x + y * y + z * z)

    companion object {
        val origin = Vector3(0.0f, 0.0f, 0.0f)
        const val G = 0.000667F
    }

}