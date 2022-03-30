package com.monstarlab.features.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.monstarlab.core.ui.theme.AppTheme

@Composable
fun ResourcesCoordinator(
    viewModel: ResourceViewModel
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchResources()
    }

    val state = viewModel.stateFlow.collectAsState(ResourcesViewState()).value
    AppTheme {
        ResourcesScreen(state)
    }
}