package com.monstarlab.features.resources

import androidx.lifecycle.*
import com.monstarlab.arch.extensions.LoadingAware
import com.monstarlab.arch.extensions.ViewErrorAware
import com.monstarlab.core.domain.model.Resource
import com.monstarlab.core.usecases.resources.GetResourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class ResourceViewModel @Inject constructor(
    private val getResourcesUseCase: GetResourcesUseCase
) : ViewModel(), ViewErrorAware, LoadingAware {

    val resourcesFlow: MutableStateFlow<List<Resource>> = MutableStateFlow(emptyList())

    init {
        loadingFlow.launchIn(viewModelScope)
        viewErrorFlow.launchIn(viewModelScope)
    }

    fun fetchResources() {
        collectFlow(getResourcesUseCase.getResources()) {
            resourcesFlow.value = it
        }
    }
}
