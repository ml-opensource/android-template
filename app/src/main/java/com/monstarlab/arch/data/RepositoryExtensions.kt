package com.monstarlab.arch.data

import com.monstarlab.core.domain.error.ErrorModel
import com.monstarlab.core.domain.error.toError
import retrofit2.Response
import java.io.IOException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


inline fun <T> safeCall(
        block: () -> Response<T>
): RepositoryResult<T>  {
    val response = block()
    val body = response.body()
    return when (response.isSuccessful && body != null) {
        true -> RepositoryResult.Success(body)
        false -> RepositoryResult.Error(response.toError())
    }
}

fun <T> Response<out T>.toResult(): RepositoryResult<T>  {
    val body = this.body()
    return when (this.isSuccessful && body != null) {
        true -> RepositoryResult.Success(body)
        false -> RepositoryResult.Error(this.toError())
    }
}

fun <T, R> Response<out T>.toResultAndMap(transform: (T) -> R): RepositoryResult<R>  {
    val body = this.body()
    return when (this.isSuccessful && body != null) {
        true -> RepositoryResult.Success(transform(body))
        false -> RepositoryResult.Error(this.toError())
    }
}



sealed class RepositoryResult<out T> {
    data class Success<T>(val value: T): RepositoryResult<T>()
    data class Error(val error: ErrorModel.Http): RepositoryResult<Nothing>()
}

fun <T> RepositoryResult<T>.onSuccess(block: (T) -> Unit): RepositoryResult<T> {
    if(this is RepositoryResult.Success) block.invoke(value)
    return this
}

fun <T> RepositoryResult<T>.onError(block: (ErrorModel.Http) -> Unit): RepositoryResult<T> {
    if(this is RepositoryResult.Error) block.invoke(error)
    return this
}

fun <T> RepositoryResult<T>.isError(): Boolean {
    return this is RepositoryResult.Error
}

val <T> RepositoryResult<T>.errorOrNull: ErrorModel.Http?
    get() {
        return if(this is RepositoryResult.Error) error else null
    }