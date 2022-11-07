package com.monstarlab.core.extensions

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.monstarlab.core.ui.errorhandling.ViewError
import com.monstarlab.core.utils.DebounceOnClickListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

fun Fragment.snackErrorFlow(
    targetFlow: SharedFlow<ViewError>,
    root: View,
    length: Int = Snackbar.LENGTH_SHORT
) {
    collectFlow(targetFlow) { viewError ->
        Snackbar.make(root, viewError.message, length).show()
    }
}

fun Fragment.visibilityFlow(targetFlow: Flow<Boolean>, vararg view: View) {
    collectFlow(targetFlow) { loading ->
        view.forEach { it.isVisible = loading }
    }
}

fun <T> Fragment.collectFlow(
    targetFlow: Flow<T>,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    collectBlock: ((T) -> Unit)
) {
    lifecycleScope.launchWhenStarted {
        targetFlow.flowWithLifecycle(viewLifecycleOwner.lifecycle, minActiveState)
            .collect {
                collectBlock(it)
            }
    }
}

fun <T1, T2> Fragment.combineFlows(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    collectBlock: ((T1, T2) -> Unit)
) {
    collectFlow(
        flow1.combine(flow2) { v1, v2 ->
            collectBlock.invoke(v1, v2)
        }
    ) {}
}

fun <T1, T2, T3> Fragment.combineFlows(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    collectBlock: ((T1, T2, T3) -> Unit)
) {
    collectFlow(
        combine(flow1, flow2, flow3) { v1, v2, v3 ->
            collectBlock.invoke(v1, v2, v3)
        }
    ) {}
}

fun <T1, T2, T3, T4> Fragment.combineFlows(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    collectBlock: ((T1, T2, T3, T4) -> Unit)
) {
    collectFlow(
        combine(flow1, flow2, flow3, flow4) { v1, v2, v3, v4 ->
            collectBlock.invoke(v1, v2, v3, v4)
        }
    ) {}
}

fun <T1, T2> Fragment.zipFlows(flow1: Flow<T1>, flow2: Flow<T2>, collectBlock: ((T1, T2) -> Unit)) {
    collectFlow(
        flow1.zip(flow2) { v1, v2 ->
            collectBlock.invoke(v1, v2)
        }
    ) {}
}

fun View.clicks(throttleTime: Long = 400): Flow<Unit> = callbackFlow {
    this@clicks.setOnClickListener {
        this.trySend(Unit).isSuccess
    }
    awaitClose { this@clicks.setOnClickListener(null) }
}.throttleFirst(throttleTime)

fun View.onClick(interval: Long = 400L, listenerBlock: (View) -> Unit) =
    setOnClickListener(DebounceOnClickListener(interval, listenerBlock))
