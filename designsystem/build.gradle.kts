@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlin.compose.compiler)
}

android {
    namespace = "com.monstarlab.designsystem"
    buildFeatures.compose = true

}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Compose
    implementation(platform(libs.android.compose.bom))
    implementation(libs.bundles.android.compose.core)
    implementation(libs.android.compose.material)
    implementation(libs.android.compose.material.windowsize)
    debugImplementation(libs.android.compose.ui.tooling)

    implementation(project(":localization"))
    implementation(project(":core"))
}