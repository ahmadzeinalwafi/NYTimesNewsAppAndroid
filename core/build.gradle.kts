import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

fun getApi(propertyKey: String): String {
    val properties = Properties()
    val localProperties = rootProject.file("local.properties")
    if (localProperties.exists()) {
        properties.load(localProperties.inputStream())
    }
    return properties.getProperty(propertyKey, "")
}

android {
    namespace = "com.example.newsapp.core"

    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        val apiKey = getApi("API_KEY")
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    // Main Dependencies
    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.material)

    // UI
    api(libs.glide)
    api(libs.recyclerview)
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.lifecycle.viewmodel.ktx)

    // Networking
    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.logging.interceptor)

    // Database
    api(libs.androidx.room.runtime)
    api(libs.androidx.room.ktx)
    implementation(libs.coil)
    api(libs.androidx.navigation.fragment.ktx)

    // DI
    api(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.9.0")

    //    Database
    ksp(libs.room.compiler)
    implementation("net.zetetic:android-database-sqlcipher:4.5.4")
    implementation("androidx.sqlite:sqlite:2.4.0")

    //Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.5.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}