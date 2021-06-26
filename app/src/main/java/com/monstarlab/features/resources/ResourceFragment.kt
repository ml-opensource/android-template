package com.monstarlab.features.resources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.monstarlab.arch.extensions.collectFlow
import com.monstarlab.core.sharedui.theme.AppTheme
import com.monstarlab.features.resources.components.ResourcesList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResourceFragment : Fragment() {

    private val viewModel by viewModels<ResourceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    ResourcesScreen(viewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchResources()

        collectFlow(viewModel.errorFlow) { errorMessage ->
            Snackbar.make(view, errorMessage.message, Snackbar.LENGTH_SHORT).show()
        }

    }
}

@Composable
fun ResourcesScreen(viewModel: ResourceViewModel) {
    val isLoading by viewModel.loadingFlow.collectAsState()
    val resources by viewModel.resourcesFlow.collectAsState()
    Scaffold {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                ResourcesList(items = resources, modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp))
            }
        }
    }
}