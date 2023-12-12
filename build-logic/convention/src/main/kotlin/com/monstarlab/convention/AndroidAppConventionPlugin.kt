package com.monstarlab.convention

import com.android.build.api.dsl.ApplicationExtension
import com.monstarlab.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configure<ApplicationExtension> {
            compileSdk = AndroidConfiguration.COMPILE_SDK
            defaultConfig {
                targetSdk = AndroidConfiguration.TARGET_SDK
                minSdk = AndroidConfiguration.MIN_SDK
            }

            buildFeatures {
                compose = true
                buildConfig = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = target.rootProject.libs.findVersion("compose_compiler").get().toString()
            }
        }

    }
}