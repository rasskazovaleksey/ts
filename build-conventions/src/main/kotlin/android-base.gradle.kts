import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    namespace = "com.github.rasskazovalexey" + project.path.replace(":", ".").replace("-", "_")
    compileSdkVersion(34)

    defaultConfig {
        minSdk = 26
        targetSdk = 34
    }
}
