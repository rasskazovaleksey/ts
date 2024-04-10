plugins {
    id("io.gitlab.arturbosch.detekt") apply false
}

tasks.getByName("detekt").enabled = false

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("detektAll") {
    parallel = true
    ignoreFailures = false
    setSource(files(projectDir))
    exclude("**/build/**")
    buildUponDefaultConfig = true
    config.setFrom(".detekt.yml")
    reports {
        xml.required.set(false)
        txt.required.set(false)
        html {
            required.set(true)
            outputLocation.set(
                File(
                    project.layout.buildDirectory.asFile.get(),
                    "reports/detekt.html"
                )
            )
        }
    }
}
