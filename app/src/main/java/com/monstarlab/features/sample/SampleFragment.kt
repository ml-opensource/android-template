package com.monstarlab.features.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.monstarlab.R
import com.monstarlab.base.BaseFragment
import com.monstarlab.databinding.FragmentSampleBinding
import com.monstarlab.extensions.collectFlow

class SampleFragment: BaseFragment(R.layout.fragment_sample) {

    val viewModel by viewModel<SampleViewModel>()

    private var _binding: FragmentSampleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSampleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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