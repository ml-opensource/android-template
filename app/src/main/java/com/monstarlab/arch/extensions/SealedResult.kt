package com.monstarlab.arch.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.io.Serializable
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * A discriminated union that encapsulates a successful outcome with a value of type [T]
 * or a failure with an arbitrary [Throwable] exception.
 */
sealed class SealedResult<out T> : Serializable {

    @PublishedApi
    internal data class Failure<out T>(val exception: Throwable) : SealedResult<T>()

    @PublishedApi
    internal data class Success<out T>(val value: T) : SealedResult<T>()

    /**
     * Returns `true` if this instance represents a failed outcome.
     * In this case [isSuccess] returns `false`.
     */
    val isFailure: Boolean
        get() = this is Failure

    /**
     * Returns `true` if this instance represents a successful outcome.
     * In this case [isFailure] returns `false`.
     */
    val isSuccess: Boolean
        get() = this is Success

    /**
     * Returns the encapsulated value if this instance represents [success][SealedResult.isSuccess] or `null`
     * if it is [failure][SealedResult.isFailure].
     *
     * This function is a shorthand for `getOrElse { null }` (see [getOrElse]) or
     * `fold(onSuccess = { it }, onFailure = { null })` (see [fold]).
     */
    public fun getOrNull(): T? = when (this) {
        is Failure -> null
        is Success -> value
    }

    /**
     * Returns the encapsulated [Throwable] exception if this instance represents [failure][isFailure] or `null`
     * if it is [success][isSuccess].
     *
     * This function is a shorthand for `fold(onSuccess = { null }, onFailure = { it })` (see [fold]).
     */
    public fun exceptionOrNull(): Throwable? = when (this) {
        is Failure -> exception
        is Success -> null
    }

    /**
     * Returns a string `Success(v)` if this instance represents [success][SealedResult.isSuccess]
     * where `v` is a string representation of the value or a string `Failure(x)` if
     * it is [failure][isFailure] where `x` is a string representation of the exception.
     */
    public override fun toString(): String = when (this) {
        is Failure -> "Failure($exception)"
        is Success -> "Success($value)"
    }

    /**
     * Companion object for [SealedResult] class that contains its constructor functions
     * [success] and [failure].
     */
    public companion object {

        /**
         * Returns an instance that encapsulates the given [value] as successful value.
         */
        public fun <T> success(value: T): SealedResult<T> = Success(value)

        /**
         * Returns an instance that encapsulates the given [Throwable] [exception] as failure.
         */
        public fun <T> failure(exception: Throwable): SealedResult<T> = Failure(exception)
    }
}

/**
 * Calls the specified function [block] and returns its encapsulated result if invocation was successful,
 * catching any [Throwable] exception that was thrown from the [block] function execution and encapsulating it as a failure.
 */
@Suppress("FunctionName", "TooGenericExceptionCaught")
public inline fun <T> SealedResult(block: () -> T): SealedResult<T> {
    return try {
        SealedResult.success(block())
    } catch (exception: Throwable) {
        SealedResult.failure(exception)
    }
}

/**
 * Returns the encapsulated value if this instance represents [success][SealedResult.isSuccess] or throws the encapsulated [Throwable] exception
 * if it is [failure][SealedResult.isFailure].
 *
 * This function is a shorthand for `getOrElse { throw it }` (see [getOrElse]).
 */
public fun <T> SealedResult<T>.getOrThrow(): T {
    return when (this) {
        is SealedResult.Failure -> throw exception
        is SealedResult.Success -> value
    }
}

/**
 * Returns the encapsulated value if this instance represents [success][SealedResult.isSuccess] or the
 * result of [onFailure] function for the encapsulated [Throwable] exception if it is [failure][SealedResult.isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [onFailure] function.
 *
 * This function is a shorthand for `fold(onSuccess = { it }, onFailure = onFailure)` (see [fold]).
 */
@OptIn(ExperimentalContracts::class)
public inline fun <R, T : R> SealedResult<T>.getOrElse(onFailure: (exception: Throwable) -> R): R {
    contract {
        callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is SealedResult.Failure -> onFailure(exception)
        is SealedResult.Success -> value
    }
}

/**
 * Returns the encapsulated value if this instance represents [success][SealedResult.isSuccess] or the
 * [defaultValue] if it is [failure][SealedResult.isFailure].
 *
 * This function is a shorthand for `getOrElse { defaultValue }` (see [getOrElse]).
 */
public fun <R, T : R> SealedResult<T>.getOrDefault(defaultValue: R): R {
    return when (this) {
        is SealedResult.Failure -> defaultValue
        is SealedResult.Success -> value
    }
}

/**
 * Returns the result of [onSuccess] for the encapsulated value if this instance represents [success][SealedResult.isSuccess]
 * or the result of [onFailure] function for the encapsulated [Throwable] exception if it is [failure][SealedResult.isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [onSuccess] or by [onFailure] function.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <R, T> SealedResult<T>.fold(
    onFailure: (exception: Throwable) -> R,
    onSuccess: (value: T) -> R
): R {
    contract {
        callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
        callsInPlace(onSuccess, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is SealedResult.Failure -> onFailure(exception)
        is SealedResult.Success -> onSuccess(value)
    }
}

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated value
 * if this instance represents [success][SealedResult.isSuccess] or the
 * original encapsulated [Throwable] exception if it is [failure][SealedResult.isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 * See [mapCatching] for an alternative that encapsulates exceptions.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <R, T> SealedResult<T>.map(transform: (value: T) -> R): SealedResult<R> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is SealedResult.Failure -> SealedResult.failure(exception)
        is SealedResult.Success -> SealedResult.success(transform(value))
    }
}

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated value
 * if this instance represents [success][SealedResult.isSuccess] or the
 * original encapsulated [Throwable] exception if it is [failure][SealedResult.isFailure].
 *
 * This function catches any [Throwable] exception thrown by [transform] function and encapsulates it as a failure.
 * See [map] for an alternative that rethrows exceptions from `transform` function.
 */
public inline fun <R, T> SealedResult<T>.mapCatching(transform: (value: T) -> R): SealedResult<R> {
    return when (this) {
        is SealedResult.Failure -> SealedResult.failure(exception)
        is SealedResult.Success -> SealedResult { transform(value) }
    }
}

/**
 * Returns a [SealedResult] of the given [transform] function applied to the encapsulated value
 * if this instance represents [success][SealedResult.isSuccess] or the
 * original encapsulated [Throwable] exception if it is [failure][SealedResult.isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 * See [flatMapCatching] for an alternative that encapsulates exceptions.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <R, T> SealedResult<T>.flatMap(transform: (value: T) -> SealedResult<R>): SealedResult<R> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is SealedResult.Failure -> SealedResult.failure(exception)
        is SealedResult.Success -> transform(value)
    }
}

/**
 * Returns a [SealedResult] of the given [transform] function applied to the encapsulated value
 * if this instance represents [success][SealedResult.isSuccess] or the
 * original encapsulated [Throwable] exception if it is [failure][SealedResult.isFailure].
 *
 * This function catches any [Throwable] exception thrown by [transform] function and encapsulates it as a failure.
 * See [map] for an alternative that rethrows exceptions from `transform` function.
 */
public inline fun <R, T> SealedResult<T>.flatMapCatching(transform: (value: T) -> SealedResult<R>): SealedResult<R> {
    return when (this) {
        is SealedResult.Failure -> SealedResult.failure(exception)
        is SealedResult.Success -> SealedResult { transform(value).getOrThrow() }
    }
}

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated [Throwable] exception
 * if this instance represents [failure][SealedResult.isFailure] or the
 * original encapsulated value if it is [success][SealedResult.isSuccess].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 * See [recoverCatching] for an alternative that encapsulates exceptions.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <R, T : R> SealedResult<T>.recover(transform: (exception: Throwable) -> R): SealedResult<R> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is SealedResult.Failure -> SealedResult.success(transform(exception))
        is SealedResult.Success -> this
    }
}

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated [Throwable] exception
 * if this instance represents [failure][SealedResult.isFailure] or the
 * original encapsulated value if it is [success][SealedResult.isSuccess].
 *
 * This function catches any [Throwable] exception thrown by [transform] function and encapsulates it as a failure.
 * See [recover] for an alternative that rethrows exceptions.
 */
public inline fun <R, T : R> SealedResult<T>.recoverCatching(transform: (exception: Throwable) -> R): SealedResult<R> {
    return when (this) {
        is SealedResult.Failure -> SealedResult { transform(exception) }
        is SealedResult.Success -> this
    }
}

/**
 * Performs the given [action] on the encapsulated [Throwable] exception if this instance represents [failure][SealedResult.isFailure].
 * Returns the original `Result` unchanged.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> SealedResult<T>.onFailure(action: (exception: Throwable) -> Unit): SealedResult<T> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is SealedResult.Failure) action(exception)
    return this
}

/**
 * Performs the given [action] on the encapsulated value if this instance represents [success][SealedResult.isSuccess].
 * Returns the original `Result` unchanged.
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> SealedResult<T>.onSuccess(action: (value: T) -> Unit): SealedResult<T> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is SealedResult.Success) action(value)
    return this
}

@OptIn(ExperimentalContracts::class)
fun <T> Flow<SealedResult<T>>.onResult(
    onFailure: suspend (exception: Throwable) -> Unit,
    onSuccess: suspend (value: T) -> Unit
): Flow<SealedResult<T>> {
    return onEach { result ->
        when (result) {
            is SealedResult.Failure -> onFailure(result.exception)
            is SealedResult.Success -> onSuccess(result.value)
        }
    }
}


