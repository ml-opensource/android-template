package com.monstarlab.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.features.nstack.domain.usecase.SetupNstackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val setupNstackUseCase: SetupNstackUseCase,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(MainActivityState())
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

}