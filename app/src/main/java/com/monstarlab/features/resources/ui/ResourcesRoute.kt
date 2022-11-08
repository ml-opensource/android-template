package com.monstarlab.features.resources.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.monstarlab.designsystem.theme.AppTheme


@Composable
fun ResourcesRoute(
    coordinator: ResourcesCoordinator
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(initial = ResourcesState())

    // UI Actions
    val actions = rememberResourcesActions(coordinator)

    // UI Rendering
    ProvideResourcesActions(actions) {
        AppTheme {
            ResourcesScreen(uiState, actions)
        }
    }
}


@Composable
fun rememberResourcesActions(coordinator: ResourcesCoordinator): ResourcesActions {
    return remember(coordinator) {
        ResourcesActions()
    }
}