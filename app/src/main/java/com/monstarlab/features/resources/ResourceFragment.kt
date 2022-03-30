package com.monstarlab.features.resources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.monstarlab.arch.extensions.collectFlow
import com.monstarlab.core.ui.createComposableView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResourceFragment : Fragment() {

    private val viewModel by viewModels<ResourceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createComposableView(requireContext()) {
            ResourcesCoordinator(viewModel = viewModel)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectFlow(viewModel.errorFlow) { errorMessage ->
            Snackbar.make(view, errorMessage.message, Snackbar.LENGTH_SHORT).show()
        }
    }
}
