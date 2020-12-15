package com.monstarlab.features.sample

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.arch.extensions.combineFlows
import com.monstarlab.arch.extensions.onError
import com.monstarlab.arch.extensions.onSuccess
import com.monstarlab.core.data.repositories.PostRepository
import com.monstarlab.core.domain.error.ErrorModel
import com.monstarlab.core.domain.mock.MockFlows
import com.monstarlab.core.domain.mock.MockSuspends
import com.monstarlab.core.usecases.blog.GetPostsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class SampleViewModel @Inject constructor(
        private val getPostsUseCase: GetPostsUseCase
): ViewModel() {

    val clickFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val textFlow: MutableStateFlow<String> = MutableStateFlow("Nothing yet")
    val errorFlow = MutableSharedFlow<ErrorModel>()

    fun clickedButton() {
        clickFlow.value++
    }

    fun fetchBlogPosts() {

        getPostsUseCase.getPosts()
                .onStart { /* start loading */  }
                .onSuccess { textFlow.value = "Found ${it.size}" }
                .onError { errorFlow.emit(it) }
                .onCompletion { /* stop loading */ }
                .launchIn(viewModelScope)

        getPostsUseCase.getPosts()
            .onStart { /* start loading */  }
            .onSuccess { textFlow.value = "Found ${it.size}" }
            .onError { errorFlow.emit(it) }
            //.onEach { blogEntries -> textFlow.value = "Found ${blogEntries.size}" }
            //.retryWhen { cause, attempt -> cause is IOException && attempt <= 2 }
            //.catch { _ -> errorFlow.emit("Something went wrong") }
            .onCompletion { /* stop loading */ }
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