import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korim.format.jpg.*
import com.soywiz.korio.file.std.*

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
    val sceneContainer = sceneContainer()

    sceneContainer.changeTo({ MyScene() })
}

class MyScene : Scene() {
    override suspend fun SContainer.sceneMain() {
        val pngBytes = resourcesVfs["korge.png"].readBytes()
        val bitmap = resourcesVfs["korge.png"].readBitmap()
        JPEG.encode(bitmap, ImageEncodingProps(quality = 0.1))
        val jpegBytes = measureTime({ JPEG.encode(bitmap, ImageEncodingProps(quality = 0.1)) }) {
            println("ENCODED in $it")
        }
        JPEG.decode(jpegBytes)
        val image = measureTime({ JPEG.decode(jpegBytes) }) {
            println("DECODED in $it")
        }
        image(image)
        text("pngBytes: ${pngBytes.size} bytes, jpegBytes: ${jpegBytes.size} bytes")
    }
}
