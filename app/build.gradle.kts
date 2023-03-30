@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version Libs.Versions.kotlin
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
                "APP_NAME" to "MonstarlabDev",
                "env" to "staging",
            )
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
        create("staging") {
            dimension = "default"
            applicationIdSuffix = ".staging"
            manifestPlaceholders += mapOf(
                "APP_NAME" to "MonstarlabStaging",
                "env" to "staging",
            )
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
        create("production") {
            dimension = "default"
            applicationIdSuffix = ".staging"
            //signingConfig signingConfigs.production
            manifestPlaceholders += mapOf(
                "APP_NAME" to "Monstarlab",
                "env" to "production",
            )
            buildConfigField("String", "API_URL", "\"https://reqres.in/api/\"")
        }
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_11.toString()))
        }
    }
}

kapt {
    correctErrorTypes = true
}

configurations {
    create("devDebugImplementation")
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
    implementation(Libs.Android.activityCompose)
    implementation(Libs.Android.splash)

    testImplementation(Libs.Test.junit)
    testImplementation(Libs.Test.mockkAndroid)
    testImplementation(Libs.Test.mockkAgent)
    testImplementation(Libs.Test.coroutinesTest)

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
    implementation(Libs.Android.Lifecycle.viewModelCompose)
    implementation(Libs.Android.Lifecycle.livedata)
    implementation(Libs.Android.Lifecycle.runtimeCompose)

    implementation(platform(Libs.Compose.bom))
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.preview)
    implementation(Libs.Compose.icons)
    implementation(Libs.Compose.windowSize)
    debugImplementation(Libs.Compose.tooling)

    implementation(Libs.Compose.coil)
    implementation(Libs.Compose.accompanistSystemUi)
    implementation(Libs.Compose.accompanistPlaceholder)
    implementation(Libs.Compose.accompanifestPager)

    implementation(Libs.nstack)
    implementation(Libs.timber)
    "devDebugImplementation"(Libs.leakCanary)
    releaseImplementation(Libs.chuckerNoOp)
    debugImplementation(Libs.chuckerDebug)

}
