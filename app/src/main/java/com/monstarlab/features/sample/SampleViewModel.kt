package com.monstarlab.features.sample

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SampleViewModel @Inject constructor(

): ViewModel() {

    val clickFlow: MutableStateFlow<Int> = MutableStateFlow(0)

    fun clickedButton() {
        clickFlow.value++
    }

}