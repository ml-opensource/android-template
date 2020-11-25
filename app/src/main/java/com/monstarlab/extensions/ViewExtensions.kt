package com.monstarlab.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

fun <T> Fragment.collectFlow(targetFlow: Flow<T>, collectBlock: ((T) -> Unit)) {
    viewLifecycleOwner.lifecycleScope.launch {
        targetFlow.collect {
            collectBlock.invoke(it)
        }
    }
}

fun <T1, T2> Fragment.combineFlows(flow1: Flow<T1>, flow2: Flow<T2>, collectBlock: ((T1, T2) -> Unit)) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow1.combine(flow2) { v1, v2 ->
            collectBlock.invoke(v1, v2)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}

fun <T1, T2, T3> Fragment.combineFlows(flow1: Flow<T1>, flow2: Flow<T2>, flow3: Flow<T3>,  collectBlock: ((T1, T2, T3) -> Unit)) {
    viewLifecycleOwner.lifecycleScope.launch {
        combine(flow1, flow2, flow3) { v1, v2, v3 ->
            collectBlock.invoke(v1, v2, v3)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}

fun <T1, T2, T3, T4> Fragment.combineFlows(flow1: Flow<T1>, flow2: Flow<T2>, flow3: Flow<T3>, flow4: Flow<T4>, collectBlock: ((T1, T2, T3, T4) -> Unit)) {
    viewLifecycleOwner.lifecycleScope.launch {
        combine(flow1, flow2, flow3, flow4) { v1, v2, v3, v4 ->
            collectBlock.invoke(v1, v2, v3, v4)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}

fun <T1, T2> Fragment.zipFlows(flow1: Flow<T1>, flow2: Flow<T2>,  collectBlock: ((T1, T2) -> Unit)) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow1.zip(flow2) { v1, v2 ->
            collectBlock.invoke(v1, v2)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}