package math.labs.solarsystem

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

class SolarSystemLauncher {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val config = LwjglApplicationConfiguration()
            LwjglApplication(SolarSystemApp(), config)
        }
    }
}