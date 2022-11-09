package com.monstarlab.features.resources.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.monstarlab.designsystem.theme.AppTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ResourcesRoute(
    coordinator: ResourcesCoordinator
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(ResourcesState())

    // UI Actions
    val actions = rememberResourcesActions(coordinator)

    // UI Rendering
    AppTheme {
        ResourcesScreen(uiState, actions)
    }
}

@Composable
fun rememberResourcesActions(coordinator: ResourcesCoordinator): ResourcesActions {
    return remember(coordinator) {
        ResourcesActions(coordinator::openResourceDetails)
    }
}
