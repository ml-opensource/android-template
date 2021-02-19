package com.monstarlab.features.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import com.monstarlab.R
import com.monstarlab.arch.base.BaseFragment
import com.monstarlab.arch.extensions.clicks
import com.monstarlab.arch.extensions.collectFlow
import com.monstarlab.arch.extensions.viewBinding
import com.monstarlab.arch.extensions.visibilityFlow
import com.monstarlab.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val viewModel by viewModel<LoginViewModel>()
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.clicks().onEach {
            viewModel.login(
                binding.loginEmailEditText.text.toString(),
                binding.loginPasswordEditText.text.toString()
            )
        }.launchIn(lifecycleScope)

        collectFlow(viewModel.loginResultFlow) {
            findNavController().navigate(R.id.resourceFragment)
        }

        collectFlow(viewModel.errorFlow) { viewError ->
            Snackbar.make(view, viewError.message, Snackbar.LENGTH_SHORT).show()
        }

        visibilityFlow(viewModel.loadingFlow, binding.loginProgressBar)

        collectFlow(viewModel.loadingFlow) { loading ->
            TransitionManager.beginDelayedTransition(binding.root)
            binding.loginEmailEditText.isEnabled = !loading
            binding.loginPasswordEditText.isEnabled = !loading
            binding.loginButton.isVisible = !loading
        }
    }
}