package com.monstarlab.features.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.extensions.combineFlows
import com.monstarlab.mock.MockFlows
import com.monstarlab.mock.MockSuspends
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SampleViewModel @Inject constructor(

): ViewModel() {

    val clickFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val textFlow: MutableStateFlow<String> = MutableStateFlow("Nothing yet")

    fun clickedButton() {
        clickFlow.value++
    }

    fun fetchString() {
        viewModelScope.combineFlows(MockFlows.mockString(), MockFlows.mockFlag()) { text, flag ->
            textFlow.value = "text: $text - flag: $flag"
        }


        MockFlows.mockString()
                .onEach { result -> textFlow.value = result }
                .catch { _ -> /* Do something with the exception */ }
                .launchIn(viewModelScope)

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { MockSuspends.fetchString() }
            textFlow.value = result
        }
    }

}