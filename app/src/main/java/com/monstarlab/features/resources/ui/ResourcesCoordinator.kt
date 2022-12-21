package com.monstarlab.features.resources.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.monstarlab.core.ui.extensions.toast
import com.monstarlab.features.resources.domain.Resource

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ResourcesCoordinator(
    val viewModel: ResourcesViewModel,
    val context: Context
) {
    val screenStateFlow = viewModel.stateFlow

    fun openResourceDetails(resource: Resource) {
        context.toast(resource.toString())
    }
}

@Composable
fun rememberResourcesCoordinator(
    viewModel: ResourcesViewModel,
    context: Context = LocalContext.current
): ResourcesCoordinator {
    return remember(viewModel, context) {
        ResourcesCoordinator(
            viewModel = viewModel,
            context = context
        )
    }
}
