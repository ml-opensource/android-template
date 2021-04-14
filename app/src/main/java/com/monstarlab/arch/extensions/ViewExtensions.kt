package com.monstarlab.arch.extensions

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.monstarlab.core.sharedui.errorhandling.ViewError
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

fun View.clicks(throttleTime: Long = 400): Flow<Unit> = callbackFlow {
    this@clicks.setOnClickListener {
        offer(Unit)
    }
    awaitClose { this@clicks.setOnClickListener(null) }
}.throttleFirst(throttleTime)

fun View.onClick(interval: Long = 400L, listenerBlock: (View) -> Unit) =
    setOnClickListener(DebounceOnClickListener(interval, listenerBlock))
