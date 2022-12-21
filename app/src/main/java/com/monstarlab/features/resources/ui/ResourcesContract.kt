package com.monstarlab.features.resources.ui

import com.monstarlab.core.error.ErrorModel
import com.monstarlab.features.resources.domain.Resource

/**
 * UI State that represents ResourcesScreen
 **/
data class ResourcesState(
    val isLoading: Boolean = false,
    val resources: List<Resource> = emptyList(),
    val error: ErrorModel? = null
)

/**
 * Resources Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ResourcesActions(
    val onResourceClick: (Resource) -> Unit = {}
)
