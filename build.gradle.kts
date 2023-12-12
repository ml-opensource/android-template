buildscript {
    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.nstack.plugin)
        classpath(libs.hilt.android.plugin)
    }
}

// TODO: Remove once https://youtrack.jetbrains.com/issue/KTIJ-19369 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.convention.android.library) apply false
    alias(libs.plugins.convention.android.application) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        buildUponDefaultConfig = true
        allRules = false
        config = files("$rootDir/detekt-config.yml")
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("**/RateReminderActions.kt")
            ktlint("1.0.1")
                .editorConfigOverride(
                    mapOf(
                        "indent_size" to 4,
                        "indent_style" to "space",
                        "ij_kotlin_imports_layout" to "*,java.**,javax.**,kotlin.**,kotlinx.**,^",
                        "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                        "ij_kotlin_allow_trailing_comma" to "true",
                        "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                        "ktlint_code_style" to "android_studio",
                    )
                )
        }
    }
}
