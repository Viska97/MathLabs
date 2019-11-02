package math.labs.solarsystem

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController

class SolarSystemApp: ApplicationAdapter() {

    private lateinit var modelBatch: ModelBatch
    private lateinit var camera: PerspectiveCamera
    private lateinit var controller: CameraInputController
    private lateinit var solarSystem: SolarSystem

    override fun create() {
        camera = PerspectiveCamera(67f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera.position.set(0f, 0f, 350f)
        camera.lookAt(0f, 0f, 0f)
        camera.near = 0.05f
        camera.far = 1000f
        camera.update()
        controller = CameraInputController(camera)
        Gdx.input.inputProcessor = controller
        modelBatch = ModelBatch()
        solarSystem = SolarSystem()
    }

    override fun render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        solarSystem.compute(Gdx.graphics.deltaTime, 100)
        controller.update()
        modelBatch.begin(camera)
        for(body in solarSystem.bodies) {
            modelBatch.render(body.instance)
        }
        modelBatch.end()
    }

    override fun dispose() {
        for(body in solarSystem.bodies) {
            body.model.dispose()
        }
    }

}