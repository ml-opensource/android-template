package com.monstarlab.features.resources.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.monstarlab.features.resources.domain.Resource


/**
 * UI State that represents ResourcesScreen
 **/
data class ResourcesState(
    val isLoading: Boolean = false,
    val resources: List<Resource> = emptyList(),
    val error: String? = null
)

/**
 * Resources Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ResourcesActions(
    val onResourceClick: (Resource) -> Unit = {}
)

/**
 * Compose Utility to retrieve actions from nested components
 **/
val LocalResourcesActions = staticCompositionLocalOf<ResourcesActions> {
    error("{NAME} Actions Were not provided, make sure ProvideResourcesActions is called")
}

@Composable
fun ProvideResourcesActions(actions: ResourcesActions, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalResourcesActions provides actions) {
        content.invoke()
    }
}

