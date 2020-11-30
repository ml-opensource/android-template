package com.monstarlab.features.sample

import android.os.Bundle
import android.view.View
import com.monstarlab.R
import com.monstarlab.base.BaseFragment
import com.monstarlab.databinding.FragmentSampleBinding
import com.monstarlab.extensions.collectFlow
import com.monstarlab.extensions.viewBinding

class SampleFragment : BaseFragment(R.layout.fragment_sample) {

    private val viewModel by viewModel<SampleViewModel>()

    private val binding by viewBinding(FragmentSampleBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectFlow(viewModel.clickFlow) { clicks ->
            binding.valueTextView.text = "Clicked $clicks times"
        }

        binding.theButton.setOnClickListener {
            viewModel.clickedButton()
        }
    }
}