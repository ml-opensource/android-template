package com.monstarlab.arch.extensions

import com.monstarlab.core.domain.error.ErrorModel
import com.monstarlab.core.domain.error.toError
import retrofit2.Response
import java.io.IOException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

inline fun <T> repoCall(
    block: () -> Response<T>
): T {
    val response = block()
    val body = response.body()
    return when (response.isSuccessful && body != null) {
        true -> body
        false -> throw response.toError()
    }
}

inline fun <T, R> Response<T>.mapSuccess(
    crossinline block: (T) -> R
): R {
    val safeBody = body()
    if(this.isSuccessful && safeBody != null) {
        return block(safeBody)
    } else {
        throw toError()
    }
}

sealed class RepositoryResult<out T> {
    data class Success<T>(val value: T): RepositoryResult<T>()
    data class Error(val error: ErrorModel): RepositoryResult<Nothing>()
}

inline fun <T> RepositoryResult<T>.onSuccess(block: (T) -> Unit): RepositoryResult<T> {
    if(this is RepositoryResult.Success) block.invoke(value)
    return this
}

inline fun <T> RepositoryResult<T>.onError(block: (ErrorModel) -> Unit): RepositoryResult<T> {
    if(this is RepositoryResult.Error) block.invoke(error)
    return this
}

fun <T> RepositoryResult<T>.isError(): Boolean {
    return this is RepositoryResult.Error
}

val <T> RepositoryResult<T>.errorOrNull: ErrorModel?
    get() {
        return if(this is RepositoryResult.Error) error else null
    }
