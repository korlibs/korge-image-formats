package korlibs.image.format.jpg

import korlibs.image.format.*
import korlibs.io.stream.*

object ExtJPEG : ImageFormat("jpg", "jpeg") {
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

    override fun readImageContainer(s: SyncStream, props: ImageDecodingProps): ImageDataContainer {
        return ImageDataContainer(ImageData(listOf(ImageFrame(JPEGDecoder.decode(s.readAll())))))
    }

    override fun writeImageContainer(image: ImageDataContainer, s: SyncStream, props: ImageEncodingProps) {
        s.writeBytes(JPEGEncoder.encode(image.mainBitmap.toBMP32(), quality = (props.quality * 100).toInt()))
    }
}
