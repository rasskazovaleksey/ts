plugins {
    id("multiplatform-library-convention")
}

dependencies {
    commonMainImplementation(project(":data:key-value"))

    commonTestImplementation(kotlin("test"))
    commonTestImplementation(libs.kotlinx.coroutines.test)
}
