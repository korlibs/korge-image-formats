package com.soywiz.korim.qr

import kotlin.test.*

class QRTest {
	@Test
	fun name() {
        val img = QR().email("test@test.com")
        assertEquals(29, img.width)
		assertEquals(29, img.height)
	}
}
