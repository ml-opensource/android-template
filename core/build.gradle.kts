@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "com.monstarlab.core"
}

kotlin {
    jvmToolchain(17)
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
    implementation(libs.android.compose.material)
    implementation(libs.bundles.google.accompanist)

    // Injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp.logger)

    // Other
    implementation(libs.timber)
    debugImplementation(libs.chucker.op)
    releaseImplementation(libs.chucker.noop)
}