package com.monstarlab.arch.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Copied from AOSP https://android.googlesource.com/platform/frameworks/support/+/67cbbea03d7036f3bd27aae897a3d44b2ee027f5/lifecycle/lifecycle-runtime-ktx/src/main/java/androidx/lifecycle/FlowExt.kt
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

/**
 * Launches and runs the given [Flow] associated with the scope of the [Lifecycle].
 *
 * The returned [Job] will be cancelled when the [Lifecycle] is [Lifecycle.State.DESTROYED].
 * @see launchIn
 */
fun <T> Flow<T>.launchIn(lifecycleOwner: LifecycleOwner): Job {
    return launchIn(lifecycleOwner.lifecycleScope)
}

/**
 * @see launchIn
 */
inline fun <T> Flow<T>.collectIn(
    scope: CoroutineScope,
    crossinline action: suspend (value: T) -> Unit
): Job {
    return scope.launch { collect(action) }
}

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> {
    var job: Job = Job().apply { complete() }

    return onCompletion { job.cancel() }.run {
        flow {
            coroutineScope {
                collect { value ->
                    if (!job.isActive) {
                        emit(value)
                        job = launch { delay(windowDuration) }
                    }
                }
            }
        }
    }
}
