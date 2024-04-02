package com.monstarlab.convention

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.BaseExtension
import com.monstarlab.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<BaseExtension> {
                if (this is CommonExtension<*, *, *, *, *>) {
                    buildFeatures { compose = true }
                    composeOptions {
                        kotlinCompilerExtensionVersion = target.rootProject
                            .libs
                            .findVersion("compose_compiler")
                            .get()
                            .toString()
                    }
                }
            }
        }
    }
}