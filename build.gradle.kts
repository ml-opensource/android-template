buildscript {
    dependencies {
        classpath(Libs.Plugins.gradle)
        classpath(Libs.Plugins.kotlin)
        classpath(Libs.Plugins.nstack)
        classpath(Libs.Plugins.hilt)
    }
}

plugins {
    kotlin("jvm") version Libs.Versions.kotlin apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0" apply false
    id("com.diffplug.spotless") version "6.15.0"
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
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/RateReminderActions.kt")
            ktlint()
        }
    }
}
