package com.monstarlab.features.resources.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.monstarlab.core.ui.extensions.animatedVisibilityItem
import com.monstarlab.designsystem.components.AppTopBar
import com.monstarlab.designsystem.theme.Theme
import com.monstarlab.features.resources.ui.components.ResourceItem

@Composable
fun ResourcesScreen(
    state: ResourcesState = ResourcesState(),
    actions: ResourcesActions = LocalResourcesActions.current
) {
    Scaffold(
        topBar = { AppTopBar(title = "Resources")}
    ) {
        LazyColumn(
            modifier = Modifier.padding(it).fillMaxSize(),
            contentPadding = PaddingValues(Theme.dimensions.medium1),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            animatedVisibilityItem("progress-indicator", state.isLoading) {
                CircularProgressIndicator()
            }



            items(state.resources) { resource ->
                ResourceItem(resource = resource)
            }
        }
    }
}

@Composable
@Preview
private fun ResourcesScreenPreview() {
    ResourcesScreen()
}

