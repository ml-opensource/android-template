package com.monstarlab.core.config

data class AppVersion(
    val versionName: String,
    val buildNumber: Int,
) {
    override fun toString(): String {
        return "$versionName ($buildNumber)"
    }
}
