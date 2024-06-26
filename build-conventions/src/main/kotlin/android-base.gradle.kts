import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    namespace = "com.github.rasskazovalexey" + project.path.replace(":", ".").replace("-", "_")
    compileSdkVersion(34)
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    defaultConfig {
        minSdk = 26
        targetSdk = 34
    }
}
