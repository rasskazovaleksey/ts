import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform") apply false
    id("com.android.library") apply false
    id("android-base") apply false
}

kotlin {
    jvm("desktop")
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.library()
    }
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()
}
