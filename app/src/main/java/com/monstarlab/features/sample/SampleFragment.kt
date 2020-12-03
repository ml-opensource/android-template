package com.monstarlab.features.sample

import android.os.Bundle
import android.view.View
import com.monstarlab.R
import com.monstarlab.base.BaseFragment
import com.monstarlab.databinding.FragmentSampleBinding
import com.monstarlab.extensions.collectFlow
import com.monstarlab.extensions.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine

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

        binding.theButton.setOnClickListener {
            viewModel.clickedButton()
        }

        viewModel.fetchString()
    }
}