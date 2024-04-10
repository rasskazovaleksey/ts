plugins {
    id("multiplatform-library-convention")
}

dependencies {
    commonMainImplementation(project(":data:key-value"))
}
