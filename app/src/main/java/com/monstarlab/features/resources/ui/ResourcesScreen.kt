package com.monstarlab.features.resources.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.monstarlab.core.ui.extensions.animatedVisibilityItem
import com.monstarlab.core.ui.previews.LightDarkPreview
import com.monstarlab.designsystem.components.AppTopBar
import com.monstarlab.designsystem.theme.AppTheme
import com.monstarlab.designsystem.theme.Theme
import com.monstarlab.features.resources.domain.Resource
import com.monstarlab.features.resources.ui.components.ResourceItem

@Composable
fun ResourcesScreen(
    state: ResourcesState = ResourcesState(),
    actions: ResourcesActions = ResourcesActions()
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Resources",
                modifier = Modifier.statusBarsPadding()
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentPadding = PaddingValues(Theme.dimensions.medium1),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Theme.dimensions.medium1)
        ) {

            animatedVisibilityItem("progress-indicator", state.isLoading) {
                CircularProgressIndicator()
            }

            items(state.resources) { resource ->
                ResourceItem(
                    resource = resource,
                    modifier = Modifier.clickable {
                        actions.onResourceClick(resource)
                    }
                )
            }
        }
    }
}

@Composable
@LightDarkPreview
private fun ResourcesScreenPreview() {
    AppTheme {
        ResourcesScreen(state = ResourcesState(resources = List(4) { Resource.Mock }))
    }
}
