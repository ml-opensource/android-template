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
import com.monstarlab.arch.extensions.collectFlow
import com.monstarlab.arch.extensions.combineFlows
import com.monstarlab.arch.extensions.snackErrorFlow
import com.monstarlab.arch.extensions.onClick
import com.monstarlab.arch.extensions.viewBinding
import com.monstarlab.arch.extensions.visibilityFlow
import com.monstarlab.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val viewModel by viewModel<LoginViewModel>()
    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.onClick {
            viewModel.login(
                binding.loginEmailEditText.text.toString(),
                binding.loginPasswordEditText.text.toString()
            )
        }

        collectFlow(viewModel.loginResultFlow) {
            findNavController().navigate(R.id.resourceFragment)
        }

        snackErrorFlow(viewModel.errorFlow, view)

        visibilityFlow(viewModel.loadingFlow, binding.loginProgressBar)

        collectFlow(viewModel.loadingFlow) { loading ->
            TransitionManager.beginDelayedTransition(binding.root)
            binding.loginEmailEditText.isEnabled = !loading
            binding.loginPasswordEditText.isEnabled = !loading
            binding.loginButton.isVisible = !loading
        }
    }
}