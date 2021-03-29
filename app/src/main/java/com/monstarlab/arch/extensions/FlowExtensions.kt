package com.monstarlab.arch.extensions

import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Flow operator that emits values from `this` upstream Flow when the [lifecycle] is
 * at least at [minActiveState] state. The emissions will be stopped when the lifecycle state
 * falls below [minActiveState] state.
 *
 * The flow will automatically start and cancel collecting from `this` upstream flow as the
 * [lifecycle] moves in and out of the target state.
 *
 * If [this] upstream Flow completes emitting items, `flowWithLifecycle` will trigger the flow
 * collection again when the [minActiveState] state is reached.
 *
 * This is NOT a terminal operator. This operator is usually followed by [collect], or
 * [onEach] and [launchIn] to process the emitted values.
 *
 * Note: this operator creates a hot flow that only closes when the [lifecycle] is destroyed or
 * the coroutine that collects from the flow is cancelled.
 *
 * ```
 * class MyActivity : AppCompatActivity() {
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         /* ... */
 *         // Launches a coroutine that collects items from a flow when the Activity
 *         // is at least started. It will automatically cancel when the activity is stopped and
 *         // start collecting again whenever it's started again.
 *         lifecycleScope.launch {
 *             flow
 *                 .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
 *                 .collect {
 *                     // Consume flow emissions
 *                  }
 *         }
 *     }
 * }
 * ```
 *
 * Warning: [Lifecycle.State.INITIALIZED] is not allowed in this API. Passing it as a
 * parameter will throw an [IllegalArgumentException].
 *
 * Tip: If multiple flows need to be collected using `flowWithLifecycle`, consider using
 * the [LifecycleOwner.addRepeatingJob] API to collect from all of them using a different
 * [launch] per flow instead. This will be more efficient as only one [LifecycleObserver] will be
 * added to the [lifecycle] instead of one per flow.
 *
 * @param lifecycle The [Lifecycle] where the restarting collecting from `this` flow work will be
 * kept alive.
 * @param minActiveState [Lifecycle.State] in which the upstream flow gets collected. The
 * collection will stop if the lifecycle falls below that state, and will restart if it's in that
 * state again.
 * @return [Flow] that only emits items from `this` upstream flow when the [lifecycle] is at
 * least in the [minActiveState].
 */
@OptIn(ExperimentalCoroutinesApi::class)
public fun <T> Flow<T>.flowWithLifecycle(
    lifecycle: Lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): Flow<T> = callbackFlow {
    lifecycle.repeatOnLifecycle(minActiveState) {
        this@flowWithLifecycle.collect {
            send(it)
        }
    }
    close()
}

fun <T1, T2> CoroutineScope.combineFlows(flow1: Flow<T1>, flow2: Flow<T2>, collectBlock: (suspend (T1, T2) -> Unit)) {
    launch {
        flow1.combine(flow2) { v1, v2 ->
            collectBlock.invoke(v1, v2)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}

fun <T1, T2, T3> CoroutineScope.combineFlows(flow1: Flow<T1>, flow2: Flow<T2>, flow3: Flow<T3>, collectBlock: (suspend (T1, T2, T3) -> Unit)) {
    launch {
        combine(flow1, flow2, flow3) { v1, v2, v3 ->
            collectBlock.invoke(v1, v2, v3)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}

fun <T1, T2, T3, T4> CoroutineScope.combineFlows(flow1: Flow<T1>, flow2: Flow<T2>, flow3: Flow<T3>, flow4: Flow<T4>, collectBlock: (suspend (T1, T2, T3, T4) -> Unit)) {
    launch {
        combine(flow1, flow2, flow3, flow4) { v1, v2, v3, v4 ->
            collectBlock.invoke(v1, v2, v3, v4)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        val mayEmit = currentTime - lastEmissionTime > windowDuration
        if (mayEmit) {
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}
