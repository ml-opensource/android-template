package com.monstarlab.arch.extensions.savedstate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class SavedStateFlowDelegate<T>(
    private val savedStateHandle: SavedStateHandle,
    private val initialValue: T
) : ReadOnlyProperty<ViewModel, MutableStateFlow<T>> {

    override fun getValue(thisRef: ViewModel, property: KProperty<*>): MutableStateFlow<T> {
        return savedStateHandle.getStateFlow(thisRef.viewModelScope, property.name, initialValue)
    }
}

fun <T> ViewModel.savedStateFlow(savedStateHandle: SavedStateHandle, initialValue: T) =
    SavedStateFlowDelegate(savedStateHandle, initialValue)