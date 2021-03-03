package com.monstarlab.arch.extensions

import android.app.Activity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

/**
 * Adds a given [observer] in a pair with a [LifecycleOwner], and this [observer] will be notified
 * about modifications of the wrapped data only if the paired [LifecycleOwner] is in active state.
 *
 * A [LifecycleOwner] is considered as active, if its state is [Lifecycle.State.STARTED] or
 * [Lifecycle.State.RESUMED].
 *
 * If the [lifecycleOwner] moves to the [Lifecycle.State.DESTROYED] state, the observer will
 * automatically be removed.
 *
 * When data changes while the [lifecycleOwner] is not active, it will not receive any updates.
 * If it becomes active again, it will receive the last available data automatically.
 *
 * [observeIn] keeps a strong reference to the observer and the owner as long as the
 * given [LifecycleOwner] is not destroyed. When it is destroyed, [observeIn] removes references
 * to the observer and the owner.
 *
 * If the given [lifecycleOwner] is already in [Lifecycle.State.DESTROYED] state, [observeIn]
 * ignores the call. If the given [lifecycleOwner], [observer] tuple is already added, the call is
 * ignored. If the [observer] is already in the list with another [lifecycleOwner], [observeIn]
 * throws an [IllegalArgumentException].
 *
 * @param lifecycleOwner: the [LifecycleOwner] which controls the observer.
 * @param observer: the observer that will receive the events.
 */
fun <T> Flow<T>.observeIn(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
    asLiveData().observe(lifecycleOwner, observer)
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

/**
 * Launches and runs the given [Flow] when the [Lifecycle] controlling this
 * [LifecycleCoroutineScope] is at least in [Lifecycle.State.CREATED] state.
 *
 * For an [Activity], this state will be reached in two cases:
 * - after [Activity.onCreate] call;
 * - right before [Activity.onStop] call.
 *
 * The returned [Job] will be cancelled when the [Lifecycle] is [Lifecycle.State.DESTROYED].
 * @see launchIn
 * @see launchWhenCreatedIn
 * @see whenCreated
 * @see Lifecycle.State.CREATED
 */
fun <T> Flow<T>.launchWhenCreatedIn(lifecycleOwner: LifecycleOwner): Job {
    return lifecycleOwner.lifecycleScope.launchWhenCreated { collect() }
}

/**
 * @see launchWhenCreatedIn
 */
inline fun <T> Flow<T>.collectWhenCreatedIn(
    lifecycleOwner: LifecycleOwner,
    crossinline action: suspend (value: T) -> Unit
): Job {
    return lifecycleOwner.lifecycleScope.launchWhenCreated { collect(action) }
}

/**
 * Launches and runs the given [Flow] when the [Lifecycle] controlling this
 * [LifecycleCoroutineScope] is at least in [Lifecycle.State.STARTED] state.
 *
 * For an [Activity], this state will be reached in two cases:
 * - after [Activity.onStart] call;
 * - right before [Activity.onPause] call.
 *
 * The returned [Job] will be cancelled when the [Lifecycle] is [Lifecycle.State.DESTROYED].
 * @see launchIn
 * @see launchWhenStartedIn
 * @see whenStarted
 * @see Lifecycle.State.STARTED
 */
fun <T> Flow<T>.launchWhenStartedIn(lifecycleOwner: LifecycleOwner): Job {
    return lifecycleOwner.lifecycleScope.launchWhenStarted { collect() }
}

/**
 * @see launchWhenStartedIn
 */
inline fun <T> Flow<T>.collectWhenStartedIn(
    lifecycleOwner: LifecycleOwner,
    crossinline action: suspend (value: T) -> Unit
): Job {
    return lifecycleOwner.lifecycleScope.launchWhenStarted { collect(action) }
}

/**
 * Launches and runs the given [Flow] when the [Lifecycle] controlling this
 * [LifecycleCoroutineScope] is at least in [Lifecycle.State.RESUMED] state.
 *
 * For an [Activity], this state will be reached in one case:
 * - after [Activity.onResume] is called.
 *
 * The returned [Job] will be cancelled when the [Lifecycle] is [Lifecycle.State.DESTROYED].
 * @see launchIn
 * @see launchWhenResumedIn
 * @see whenResumed
 * @see Lifecycle.State.RESUMED
 */
fun <T> Flow<T>.launchWhenResumedIn(lifecycleOwner: LifecycleOwner): Job {
    return lifecycleOwner.lifecycleScope.launchWhenResumed { collect() }
}

/**
 * @see launchWhenResumedIn
 */
inline fun <T> Flow<T>.collectWhenResumedIn(
    lifecycleOwner: LifecycleOwner,
    crossinline action: suspend (value: T) -> Unit
): Job {
    return lifecycleOwner.lifecycleScope.launchWhenResumed { collect(action) }
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
