package com.monstarlab.convention

import com.android.build.gradle.LibraryExtension
import com.monstarlab.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                compileSdk = AndroidConfiguration.COMPILE_SDK
                defaultConfig {
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
}
