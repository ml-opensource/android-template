
@file:Suppress("UnstableApiUsage")
// TODO: Remove once https://youtrack.jetbrains.com/issue/KTIJ-19369 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.kotlin.serialization)
    id("dk.nstack.translation.plugin")
    id("dagger.hilt.android.plugin")
}

val nStackKey = "LqWLm621BwIxNRzdrei88pKhIIEI2EE8ni8r"
val nStackAppId = "IXmpT4N7MJbGEXvDfGqGH4UKHrmV0EOqFeK0"

translation {
    appId = nStackAppId
    apiKey = nStackKey
    acceptHeader = "en-GB"
}

android {
    compileSdk = 34
    namespace = "com.monstarlab"
    flavorDimensions += "default"
    defaultConfig {
        manifestPlaceholders += mapOf("appId" to nStackAppId, "apiKey" to nStackKey)
        applicationId = "com.monstarlab"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    productFlavors {
        create("dev") {
            manifestPlaceholders += mapOf("APP_NAME" to "MonstarlabDev", "env" to "staging")
            dimension = "default"
            applicationIdSuffix = ".dev"
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
        create("staging") {
            manifestPlaceholders += mapOf("APP_NAME" to "MonstarlabStaging", "env" to "staging")
            dimension = "default"
            applicationIdSuffix = ".staging"
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
        create("production") {
            manifestPlaceholders += mapOf("APP_NAME" to "Monstarlab", "env" to "production")
            dimension = "default"
            applicationIdSuffix = ".staging"
            //signingConfig signingConfigs.production
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.toString()))
        }
    }

    packaging {
        resources.excludes.add("META-INF/versions/9/previous-compilation-data.bin")
    }
}

kapt {
    correctErrorTypes = true
}

configurations {
    create("devDebugImplementation")
}

dependencies {
    // Kotlin
    implementation(libs.bundles.kotlin)
    implementation(libs.kotlin.serialization.json)

    // Android
    implementation(libs.bundles.android.core)
    implementation(libs.android.splash)

    implementation(libs.bundles.android.lifecycle)

    implementation(libs.android.navigation.fragment)
    implementation(libs.android.navigation.ui)

    implementation(libs.android.lifecycle.runtime.compose)
    implementation(libs.android.datastore.preferences)

    // Compose
    implementation(platform(libs.android.compose.bom))
    implementation(libs.bundles.android.compose.core)
    implementation(libs.android.activity.compose)
    implementation(libs.android.lifecycle.viewmodel.compose)
    implementation(libs.bundles.google.accompanist)
    debugImplementation(libs.android.compose.ui.tooling)

    // Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Networking
    implementation(libs.retrofit.converter)
    implementation(libs.retrofit)
    implementation(libs.okhttp.logger)

    implementation(libs.nstack)
    implementation(libs.timber)
    "devDebugImplementation"(libs.leakcanary)
    debugImplementation(libs.chucker.op)
    releaseImplementation(libs.chucker.noop)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.android.test)

}
