import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath(":build-conventions")
    }
}

plugins {
    id("configuration-detekt")
}

subprojects {
    apply(plugin = "configuration-ktlint")
    setupJavaTarget(this)
}

fun setupJavaTarget(project: Project) {
    project.tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
    project.tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_11.toString()
        targetCompatibility = JavaVersion.VERSION_11.toString()
    }
}
