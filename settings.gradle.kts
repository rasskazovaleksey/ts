pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "TransactionalStore"

includeBuild("build-conventions")

include(
    ":app",
    ":data:key-value",
    ":data:key-value-in-memory-impl",
    ":domain:storage",
    ":design-system:theme",
    ":design-system:screen",
)
