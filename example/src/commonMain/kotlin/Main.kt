import korlibs.time.*
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.korge.view.*
import korlibs.korge.view.filter.*
import korlibs.image.color.*
import korlibs.image.format.*
import korlibs.image.format.jpg.*
import korlibs.image.qr.*
import korlibs.io.file.std.*
import korlibs.math.geom.*

suspend fun main() = Korge(windowSize = Size(512, 512), backgroundColor = Colors["#2b2b2b"]).start {
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
        image(image).alpha(0.25)
        text("pngBytes: ${pngBytes.size} bytes, jpegBytes: ${jpegBytes.size} bytes")
        
        image(QR.msg("Hello from KorIM-QR!")).xy(128, 128).scale(6.0).also { it.smoothing = false }//.filters(DropshadowFilter(0.0, 0.0, blurRadius = 12.0, shadowColor = Colors.BLACK))
    }
}
