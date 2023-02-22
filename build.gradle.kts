buildscript {
    dependencies {
        classpath(Libs.Plugins.gradle)
        classpath(Libs.Plugins.kotlin)
        classpath(Libs.Plugins.nstack)
        classpath(Libs.Plugins.hilt)
    }
}

plugins {
    id("com.diffplug.spotless") version "5.9.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.19.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
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
            ktlint()
        }
    }
}
