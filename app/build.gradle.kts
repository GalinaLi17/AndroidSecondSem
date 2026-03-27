import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.compose)
}
android {
    namespace = "ru.itis.android.uprising26"

    defaultConfig {
        applicationId = "ru.itis.android.uprising26"
        versionCode = 1
        versionName = "1.0"


        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")

        val geniusApiKey = if (localPropertiesFile.exists()) {
            localProperties.load(FileInputStream(localPropertiesFile))
            localProperties.getProperty("GENIUS_API_KEY", "")
        } else {
            ""
        }

        buildConfigField("String", "GENIUS_API_BASE_URL", "\"https://api.genius.com/\"")
        buildConfigField("String", "apiKey", "\"$geniusApiKey\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit.v2110)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.gson)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.play.services.base)

    implementation("androidx.navigation:navigation-compose:2.8.9")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
    implementation("io.coil-kt:coil-compose:2.7.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.x.lifecycle.runtime.ktx)
    implementation(libs.x.activity.compose)

}