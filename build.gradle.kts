import korlibs.korge.gradle.*

plugins {
    alias(libs.plugins.korge)
}

korge {
    id = "org.korge.examples.jitto"

    targetJvm()
    targetJs()
    targetDesktop()
    //targetDesktopCross()
    targetIos()
    targetAndroid()

    serializationJson()
}

dependencies {
    add("commonMainApi", project(":deps"))
}

