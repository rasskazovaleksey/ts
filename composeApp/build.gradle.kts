plugins {
    id("multiplatform-application-convention")
    alias(libs.plugins.jetbrains.compose)
}

dependencies {
    commonMainImplementation(compose.material3)
    commonMainImplementation(compose.foundation)
    commonMainImplementation(compose.runtime)

    androidMainImplementation(libs.androidx.activity.compose)
    desktopMainImplementation(compose.desktop.currentOs)

    commonMainImplementation(project(":design-system:theme"))
    commonMainImplementation(project(":design-system:screen"))
    commonMainImplementation(project(":domain:storage"))
    commonMainImplementation(project(":data:key-value"))
    commonMainImplementation(project(":data:key-value-in-memory-impl"))
}

compose.desktop {
    application {
        mainClass = "com.github.rasskazovalexey.app.MainKt"
    }
}

compose.experimental {
    web.application {}
}
