package korlibs.image.format.jpg

import korlibs.image.format.*
import korlibs.io.stream.*

object JPEG : ImageFormat("jpg", "jpeg") {
    override fun decodeHeader(s: SyncStream, props: ImageDecodingProps): ImageInfo? = try {
        val info = JPEGDecoder.decodeInfo(s.readAll())
        ImageInfo().apply {
            this.width = info.width
            this.height = info.height
            this.bitsPerPixel = 24
        }
    } catch (e: Throwable) {
        null
    }

    override fun readImage(s: SyncStream, props: ImageDecodingProps): ImageData {
        return ImageData(listOf(ImageFrame(JPEGDecoder.decode(s.readAll()))))
    }

    override fun writeImage(image: ImageData, s: SyncStream, props: ImageEncodingProps) {
        s.writeBytes(JPEGEncoder.encode(image.mainBitmap.toBMP32(), quality = (props.quality * 100).toInt()))
    }
}
