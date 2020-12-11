package com.monstarlab.features.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.arch.extensions.combineFlows
import com.monstarlab.core.data.repositories.PostRepository
import com.monstarlab.core.domain.mock.MockFlows
import com.monstarlab.core.domain.mock.MockSuspends
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class SampleViewModel @Inject constructor(
    private val postRepository: PostRepository
): ViewModel() {

    val clickFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val textFlow: MutableStateFlow<String> = MutableStateFlow("Nothing yet")
    val errorFlow = MutableSharedFlow<String>()

    fun clickedButton() {
        clickFlow.value++
    }

    fun fetchBlogPosts() {
        postRepository
            .getPosts()
            .onEach { blogEntries -> textFlow.value = "Found ${blogEntries.size}" }
            .catch { _ -> errorFlow.emit("Something went wrong") }
            .launchIn(viewModelScope)
    }

    fun fetchString() {
        /*
        viewModelScope.combineFlows(MockFlows.mockString(), MockFlows.mockFlag()) { text, flag ->
            textFlow.value = "text: $text - flag: $flag"
        }

         */

        MockFlows.mockString()
                .onEach { result -> textFlow.value = result }
                .retry(3) { t -> t is IOException }
                .catch { _ -> textFlow.value = "ERROR :(((" }
                .launchIn(viewModelScope)

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { MockSuspends.fetchString() }
            textFlow.value = result
        }
    }

}