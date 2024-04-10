plugins {
    id("multiplatform-library-convention")
    alias(libs.plugins.jetbrains.compose)
}

dependencies {
    commonMainImplementation(compose.material3)
}
