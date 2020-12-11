package com.monstarlab.features.sample

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.monstarlab.R
import com.monstarlab.arch.base.BaseFragment
import com.monstarlab.databinding.FragmentSampleBinding
import com.monstarlab.arch.extensions.collectFlow
import com.monstarlab.arch.extensions.viewBinding

class SampleFragment : BaseFragment(R.layout.fragment_sample) {

    private val viewModel by viewModel<SampleViewModel>()

    private val binding by viewBinding(FragmentSampleBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectFlow(viewModel.clickFlow) { clicks ->
            binding.valueTextView.text = "Clicked $clicks times"
        }

        collectFlow(viewModel.textFlow) { newText ->
            binding.asyncTextView.text = newText
        }

        collectFlow(viewModel.errorFlow) { errorMessage ->
            Snackbar.make(view, errorMessage, Snackbar.LENGTH_SHORT).show()
        }

        binding.theButton.setOnClickListener {
            //viewModel.clickedButton()
            viewModel.fetchString()
        }

        viewModel.fetchString()
    }
}