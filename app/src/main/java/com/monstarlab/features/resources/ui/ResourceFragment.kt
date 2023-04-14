package com.monstarlab.features.resources.ui

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.monstarlab.core.ui.ComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResourceFragment : ComposeFragment() {

    private val viewModel by viewModels<ResourcesViewModel>()

    override val content: @Composable () -> Unit = {
        val coordinator = rememberResourcesCoordinator(viewModel = viewModel)
        ResourcesRoute(coordinator)
    }
}
