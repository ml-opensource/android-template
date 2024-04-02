import dk.nstack.kotlin.plugin.TranslationExtension

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.convention.android.library)
    id("dk.nstack.translation.plugin")
}


val nStackKey = "LqWLm621BwIxNRzdrei88pKhIIEI2EE8ni8r"
val nStackAppId = "IXmpT4N7MJbGEXvDfGqGH4UKHrmV0EOqFeK0"

configure<TranslationExtension> {
    appId = nStackAppId
    apiKey = nStackKey
    acceptHeader = "en-GB"
}

android {
    namespace = "com.monstarlab.localization"
    defaultConfig {
        manifestPlaceholders += mapOf("appId" to nStackAppId, "apiKey" to nStackKey)
    }

}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.nstack)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.android)
    androidTestImplementation(libs.espresso.core)
}