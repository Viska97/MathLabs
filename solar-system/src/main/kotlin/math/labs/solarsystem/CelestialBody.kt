package math.labs.solarsystem

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.VertexAttributes
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.math.Quaternion
import com.badlogic.gdx.math.Vector3

class CelestialBody(val name: String,
                    val color: Color,
                    val mass: Float,
                    var radius: Float,
                    r: Vector3 = Vector3(0f, 0f, 0f),
                    var v: Vector3 = Vector3(0f, 0f, 0f),
                    var a: Vector3 = Vector3(0f, 0f, 0f)) {

    var position : Vector3 = r
        set(value) {
            field = value
            setPosition()
        }

    val model = ModelBuilder().createSphere(radius*2, radius*2, radius*2, 24, 24,
            Material(ColorAttribute.createDiffuse(color)),
            (VertexAttributes.Usage.Position or VertexAttributes.Usage.Normal).toLong())
    val instance = ModelInstance(model)

    init {
        setPosition()
    }

    private fun setPosition() {
        instance.transform.set(position, Quaternion(0f, 0f, 0f, 0f))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CelestialBody
        if (name != other.name) return false
        return true
    }

    override fun hashCode() = name.hashCode()

}