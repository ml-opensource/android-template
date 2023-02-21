@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.8.10"
    id("dk.nstack.translation.plugin")
    id("dagger.hilt.android.plugin")
}

configure<dk.nstack.kotlin.plugin.TranslationExtension> {
    appId = NStackKeys.appId
    apiKey = NStackKeys.apiKey
    acceptHeader = NStackKeys.acceptHeader
}

android {
    compileSdk = 33
    flavorDimensions += "default"
    defaultConfig {
        applicationId = "com.monstarlab"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        manifestPlaceholders += mapOf(
            "appId" to NStackKeys.appId,
            "apiKey" to NStackKeys.apiKey,
        )
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
            dimension = "default"
            applicationIdSuffix = ".dev"
            manifestPlaceholders += mapOf(
                "appId" to "MonstarlabDev",
                "apiKey" to "staging",
            )
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
        create("staging") {
            dimension = "default"
            applicationIdSuffix = ".staging"
            manifestPlaceholders += mapOf(
                "appId" to "MonstarlabStaging",
                "apiKey" to "staging",
            )
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
        create("production") {
            dimension = "default"
            applicationIdSuffix = ".staging"
            //signingConfig signingConfigs.production
            manifestPlaceholders += mapOf(
                "appId" to "Monstarlab",
                "apiKey" to "production",
            )
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.Kotlin.coroutines)
    implementation(Libs.Kotlin.coroutinesAndroid)
    implementation(Libs.Kotlin.serialization)

    implementation(Libs.Android.fragment)
    implementation(Libs.Android.core)
    implementation(Libs.Android.appCompat)
    implementation(Libs.Android.material)
    implementation(Libs.Android.contraintLayout)
    implementation(Libs.Android.datastore)

    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.Test.junitAndroid)
    androidTestImplementation(Libs.Test.androidEspresso)

    implementation(Libs.Injection.hilt)
    kapt(Libs.Injection.hiltKapt)

    implementation(Libs.Networking.retrofit)
    implementation(Libs.Networking.retrofitSerializer)
    implementation(Libs.Networking.interceptor)

    implementation(Libs.Android.Navigation.fragNavigation)
    implementation(Libs.Android.Navigation.navigationUi)

    implementation(Libs.Android.Lifecycle.core)
    implementation(Libs.Android.Lifecycle.runtime)
    implementation(Libs.Android.Lifecycle.common)
    implementation(Libs.Android.Lifecycle.viewModel)
    implementation(Libs.Android.Lifecycle.livedata)
    implementation(Libs.Android.Lifecycle.runtimeCompose)


//    // Compose
//    def composeBom = platform('androidx.compose:compose-bom:2022.10.00')
//    implementation(composeBom)
//    implementation("androidx.compose.material:material")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    implementation("androidx.compose.material:material-icons-extended")
//    implementation("androidx.activity:activity-compose:1.5.1")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
//    implementation("androidx.compose.material3:material3-window-size-class")
//

    implementation(Libs.Compose.coil)
    implementation(Libs.Compose.accompanistSystemUi)
    implementation(Libs.Compose.accompanistPlaceholder)
    implementation(Libs.Compose.accompanifestPager)

    implementation(Libs.nstack)
    implementation(Libs.timber)

}
