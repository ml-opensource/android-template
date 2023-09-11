package com.monstarlab.core.network.errorhandling.extensions

import kotlinx.coroutines.CancellationException

inline fun <reified T> Any.castAs(): T {
    return this as T
}

inline fun <reified T> Any.safeCastAs(): T? {
    return this as? T
}

/**
 * Attempts [block], returning a successful [Result] if it succeeds, otherwise a [Result.Failure]
 * taking care not to break structured concurrency
 *
 * @see <a href=" https://github.com/Kotlin/kotlinx.coroutines/issues/1814">kotlinx.coroutines discussion</a>
 */
suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    Result.failure(exception)
}
