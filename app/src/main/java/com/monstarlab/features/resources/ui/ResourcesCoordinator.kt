package com.monstarlab.features.resources.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ResourcesCoordinator(
    val viewModel: ResourcesViewModel
) {
    val screenStateFlow = viewModel.stateFlow
}

@Composable
fun rememberResourcesCoordinator(
    viewModel: ResourcesViewModel
): ResourcesCoordinator {
    return remember(viewModel) {
        ResourcesCoordinator(
            viewModel = viewModel
        )
    }
}
