package com.monstarlab.modularization

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

internal fun configureAndroid(commonExtension: CommonExtension<*, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = 34
        flavorDimensions += "default"
        defaultConfig {
            minSdk = 23
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.3"
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        buildFeatures.buildConfig = true
    }
}
