@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    compileSdk = 34
    namespace = "com.monstarlab.core"
    flavorDimensions += "default"
    defaultConfig {
        minSdk = 23
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_11.toString()))
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // Kotlin
    implementation(libs.bundles.kotlin)
    implementation(libs.kotlin.serialization.json)

    // Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp.logger)

    // Other
    implementation(libs.timber)
    debugImplementation(libs.chucker.op)
    releaseImplementation(libs.chucker.noop)
}