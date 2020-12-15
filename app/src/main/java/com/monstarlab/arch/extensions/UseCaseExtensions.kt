package com.monstarlab.arch.extensions

import com.monstarlab.core.domain.error.ErrorModel
import com.monstarlab.core.domain.error.toError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform


sealed class UseCaseResult<out T> {
    data class Success<T>(val value: T) : UseCaseResult<T>()
    data class Error(val error: ErrorModel) : UseCaseResult<Nothing>()
}

suspend inline fun <T> safeUseCase(
        crossinline block: suspend () -> RepositoryResult<T>
): UseCaseResult<T> = try {
    when (val repoResult = block()) {
        is RepositoryResult.Success -> UseCaseResult.Success(repoResult.value)
        is RepositoryResult.Error -> UseCaseResult.Error(repoResult.error)
    }
} catch (e: Exception) {
    UseCaseResult.Error(e.toError())
}

inline fun <T> safeFlow(
        crossinline block: suspend () -> RepositoryResult<T>
): Flow<UseCaseResult<T>> = flow {
    try {
        when (val repoResult = block()) {
            is RepositoryResult.Success -> UseCaseResult.Success(repoResult.value)
            is RepositoryResult.Error -> UseCaseResult.Error(repoResult.error)
        }
    } catch (e: Exception) {
        UseCaseResult.Error(e.toError())
    }
}

fun <T> Flow<UseCaseResult<T>>.onSuccess(action: suspend (T) -> Unit): Flow<UseCaseResult<T>> = transform { result ->
    if(result is UseCaseResult.Success<T>) {
        action(result.value)
    }
    return@transform emit(result)
}

fun <T> Flow<UseCaseResult<T>>.onError(action: suspend (ErrorModel) -> Unit): Flow<UseCaseResult<T>> = transform { result ->
    if(result is UseCaseResult.Error) {
        action(result.error)
    }
    return@transform emit(result)
}