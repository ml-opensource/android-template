package com.monstarlab.features.resources

import com.monstarlab.core.domain.model.Resource

data class ResourcesViewState(
    val items: List<Resource> = listOf(),
    val isLoading: Boolean = false
)