plugins {
    id("multiplatform-application-convention")
    alias(libs.plugins.jetbrains.compose)
}

dependencies {
    commonMainImplementation(compose.material3)
    commonMainImplementation(compose.foundation)
    commonMainImplementation(compose.runtime)

    androidMainImplementation(libs.androidx.activity.compose)

    commonMainImplementation(project(":design-system:theme"))
    commonMainImplementation(project(":design-system:screen"))
}
