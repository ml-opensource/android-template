package com.monstarlab.features.main

import androidx.lifecycle.*
import com.monstarlab.features.nstack.domain.usecase.SetupNstackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.nodes.nstack.kotlin.models.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val setupNstackUseCase: SetupNstackUseCase,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(State())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        setupNstack()
    }

    fun onNstackDataConsumed() = viewModelScope.launch {
        _stateFlow.update { it.copy(nstackData = null) }
    }

    private fun setupNstack() = viewModelScope.launch {
        val data = setupNstackUseCase().getOrNull()
        _stateFlow.update {
            it.copy(
                nstackData = data,
                showSplash = false
            )
        }
    }

    data class State(
        val showSplash: Boolean = true,
        val nstackData: AppOpenData? = null
    )

}