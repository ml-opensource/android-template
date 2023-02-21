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
   // id("dagger.hilt.android.plugin") version "2.45" apply false
}

allprojects {
    apply(from = "$rootDir/detekt.gradle")
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            ktlint()
        }
    }
}
