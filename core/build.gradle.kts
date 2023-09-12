@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "com.monstarlab.core"
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

    // Android
    implementation(libs.android.datastore.preferences)

    // Compose
    implementation(platform(libs.android.compose.bom))
    implementation(libs.bundles.android.compose.core)
    implementation(libs.bundles.google.accompanist)
    debugImplementation(libs.android.compose.ui.tooling)

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