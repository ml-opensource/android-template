package com.monstarlab.features.resources

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.monstarlab.features.resources.components.ResourcesList

@Composable
fun ResourcesScreen(viewModel: ResourceViewModel) {
    val isLoading by viewModel.loadingFlow.collectAsState()
    val resources by viewModel.resourcesFlow.collectAsState()
    Scaffold {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                ResourcesList(
                    items = resources,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewResourcesScreen() {
    ResourcesScreen(viewModel = viewModel())
}
