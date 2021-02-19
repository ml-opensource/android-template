package com.monstarlab.arch.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.monstarlab.core.sharedui.errorhandling.ViewError
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

fun Fragment.snackErrorFlow(targetFlow: SharedFlow<ViewError>, root: View, length: Int = Snackbar.LENGTH_SHORT) {
    collectFlow(targetFlow) { viewError ->
        Snackbar.make(root, viewError.message, length).show()
    }
}

fun <T> Fragment.collectFlow(targetFlow: Flow<T>, collectBlock: ((T) -> Unit)) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            targetFlow.collect {
                collectBlock.invoke(it)
            }
        }
    }
}

private inline fun Fragment.safeViewCollect(crossinline viewOwner: LifecycleOwner.() -> Unit) {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            viewLifecycleOwnerLiveData.observe(
                this@safeViewCollect,
                { viewLifecycleOwner ->
                    viewLifecycleOwner.viewOwner()
                })
        }
    })
}

fun <T1, T2> Fragment.combineFlows(flow1: Flow<T1>, flow2: Flow<T2>, collectBlock: ((T1, T2) -> Unit)) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            flow1.combine(flow2) { v1, v2 ->
                collectBlock.invoke(v1, v2)
            }.collect {
                // Empty collect block to trigger ^
            }
        }
    }
}

fun <T1, T2, T3> Fragment.combineFlows(flow1: Flow<T1>, flow2: Flow<T2>, flow3: Flow<T3>,  collectBlock: ((T1, T2, T3) -> Unit)) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            combine(flow1, flow2, flow3) { v1, v2, v3 ->
                collectBlock.invoke(v1, v2, v3)
            }.collect {
                // Empty collect block to trigger ^
            }
        }
    }
}

fun <T1, T2, T3, T4> Fragment.combineFlows(flow1: Flow<T1>, flow2: Flow<T2>, flow3: Flow<T3>, flow4: Flow<T4>, collectBlock: ((T1, T2, T3, T4) -> Unit)) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            combine(flow1, flow2, flow3, flow4) { v1, v2, v3, v4 ->
                collectBlock.invoke(v1, v2, v3, v4)
            }.collect {
                // Empty collect block to trigger ^
            }
        }
    }
}

fun <T1, T2> Fragment.zipFlows(flow1: Flow<T1>, flow2: Flow<T2>,  collectBlock: ((T1, T2) -> Unit)) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            flow1.zip(flow2) { v1, v2 ->
                collectBlock.invoke(v1, v2)
            }.collect {
                // Empty collect block to trigger ^
            }
        }
    }
}

fun View.clicks(throttleTime: Long = 400): Flow<Unit> = callbackFlow {
    this@clicks.setOnClickListener {
        offer(Unit)
    }
    awaitClose { this@clicks.setOnClickListener(null) }
}.throttleFirst(throttleTime)

fun View.onClick(listenerBlock: (View) -> Unit) =
    setOnClickListener(DebounceOnClickListener(listenerBlock =  listenerBlock))