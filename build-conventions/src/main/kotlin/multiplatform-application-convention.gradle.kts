import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") apply false
    id("com.android.application") apply false
    id("android-base") apply false
}

kotlin {
    jvm("desktop")
    js(IR) {
        browser()
        binaries.executable()
    }
    iosX64 {
        iOsConfigure()
    }
    iosArm64 {
        iOsConfigure()
    }
    androidTarget()
}

fun KotlinNativeTarget.iOsConfigure() {
    binaries {
        executable {
            entryPoint = "main"
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-linker-option",
                "-framework",
                "-linker-option",
                "Metal",
            ) + listOf(
                "-linker-option",
                "-framework",
                "-linker-option",
                "CoreText",
            ) + listOf("-linker-option", "-framework", "-linker-option", "CoreGraphics")
            freeCompilerArgs = freeCompilerArgs + "-Xdisable-phases=VerifyBitcode"
        }
    }
}

