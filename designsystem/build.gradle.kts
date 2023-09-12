@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.convention.android.library)
}

android {
    namespace = "com.monstarlab.designsystem"
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_11.toString()))
    }
}

dependencies {
    // Compose
    implementation(platform(libs.android.compose.bom))
    implementation(libs.bundles.android.compose.core)
    debugImplementation(libs.android.compose.ui.tooling)

    implementation(project(":core"))
}