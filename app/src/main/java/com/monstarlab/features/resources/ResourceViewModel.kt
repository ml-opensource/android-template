package com.monstarlab.features.resources

import androidx.lifecycle.*
import com.monstarlab.arch.extensions.savedstate.getStateFlow
import com.monstarlab.arch.extensions.onError
import com.monstarlab.arch.extensions.onSuccess

import com.monstarlab.arch.extensions.savedstate.savedStateFlow
import com.monstarlab.core.domain.model.Resource
import com.monstarlab.core.sharedui.errorhandling.ViewError
import com.monstarlab.core.sharedui.errorhandling.mapToViewError

import com.monstarlab.core.usecases.resources.GetResourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ResourceViewModel @Inject constructor(
    private val getResourcesUseCase: GetResourcesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val countFlow by savedStateFlow(savedStateHandle, 0)
    val countLiveData: LiveData<Int>
        get() = countFlow.asLiveData()
    val loadingFlow = savedStateHandle.getStateFlow(viewModelScope, "isLoading", false)
    val resourcesFlow =
        savedStateHandle.getStateFlow(viewModelScope, "resource", emptyList<Resource>())

    val errorFlow = MutableSharedFlow<ViewError>()

    fun fetchResources() {
        getResourcesUseCase
            .getResources()
            .onStart {
                loadingFlow.emit(true)
            }.onSuccess {
                resourcesFlow.value = it
            }.onError {
                errorFlow.emit(it.mapToViewError())
            }.onCompletion {
                loadingFlow.emit(false)
            }.launchIn(viewModelScope)
    }
}
