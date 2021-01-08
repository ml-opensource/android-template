// Package set to androidx.lifecycle so we can have access to package private methods

package androidx.lifecycle

import com.monstarlab.arch.extensions.UseCaseResult
import com.monstarlab.arch.extensions.onError
import com.monstarlab.core.sharedui.errorhandling.ViewError
import com.monstarlab.core.sharedui.errorhandling.mapToViewError
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val ERROR_FLOW_KEY = "androidx.lifecycle.ErrorFlow"
private const val LOADING_FLOW_KEY = "androidx.lifecycle.LoadingFlow"

fun ViewModel.sendViewError(viewError: ViewError) {
    viewModelScope.launch {
        getErrorMutableSharedFlow().emit(viewError)
    }
}

suspend fun ViewModel.emitViewError(viewError: ViewError) {
    getErrorMutableSharedFlow().emit(viewError)
}

val ViewModel.viewErrorFlow: SharedFlow<ViewError>
    get() {
        return getErrorMutableSharedFlow()
    }

val ViewModel.loadingFlow: StateFlow<Boolean>
    get() {
        return getLoadingMutableStateFlow()
    }


private fun ViewModel.getLoadingMutableStateFlow(): MutableStateFlow<Boolean> {
    val flow: MutableStateFlow<Boolean>? = getTag(LOADING_FLOW_KEY)
    return flow ?: setTagIfAbsent(LOADING_FLOW_KEY, MutableStateFlow(false))
}

private fun ViewModel.getErrorMutableSharedFlow(): MutableSharedFlow<ViewError> {
    val flow: MutableSharedFlow<ViewError>? = getTag(ERROR_FLOW_KEY)
    return flow ?: setTagIfAbsent(ERROR_FLOW_KEY, MutableSharedFlow())
}

fun <T> Flow<T>.bindLoading(viewModel: ViewModel): Flow<T> {
    return this
            .onStart {
                viewModel.getLoadingMutableStateFlow().value = true
            }
            .onCompletion {
                viewModel.getLoadingMutableStateFlow().value = false
            }
}
fun <T> Flow<UseCaseResult<T>>.bindError(viewModel: ViewModel): Flow<UseCaseResult<T>> {
    return this
            .onError {
                viewModel.emitViewError(it.mapToViewError())
            }
}
