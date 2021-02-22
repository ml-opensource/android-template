package com.monstarlab.features.resources

import android.os.Bundle
import android.view.View
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import com.monstarlab.R
import com.monstarlab.arch.base.BaseFragment
import com.monstarlab.arch.extensions.collectFlow
import com.monstarlab.arch.extensions.viewBinding
import com.monstarlab.databinding.FragmentResourceBinding

class ResourceFragment : BaseFragment(R.layout.fragment_resource) {

    private val viewModel by viewModel<ResourceViewModel>()
    private val binding by viewBinding(FragmentResourceBinding::bind)
    private val resourceAdapter = ResourceAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.resourceRecyclerView) {
            adapter = resourceAdapter
        }

        collectFlow(viewModel.errorFlow) { errorMessage ->
            Snackbar.make(view, errorMessage.message, Snackbar.LENGTH_SHORT).show()
        }

        collectFlow(viewModel.resourcesFlow) { resources ->
            resourceAdapter.updateResources(resources)
            resourceAdapter.notifyDataSetChanged()
        }

        collectFlow(viewModel.loadingFlow) { loading ->
            TransitionManager.beginDelayedTransition(binding.root)
            binding.resourceRecyclerView.visibility = if (loading) View.GONE else View.VISIBLE
            binding.resourceProgressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        viewModel.fetchResources()
    }
}
