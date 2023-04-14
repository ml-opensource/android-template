package com.monstarlab.features.resources.domain

import kotlinx.serialization.Serializable

@Serializable
data class Resource(
    val id: Int,
    val name: String,
    val year: Int,
    val color: String,
    val pantoneValue: String
) {
    companion object {
        val Mock = Resource(0, "Cyberpunk", 2077, "#f2e900", "")
    }
}
