package com.monstarlab.core.error

import com.monstarlab.core.network.errorhandling.ApiException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed interface ErrorModel {

    data class ApiError(val exception: ApiException) : ErrorModel

    sealed class Connection : ErrorModel {
        object Timeout : Connection()
        object IOError : Connection()
        object UnknownHost : Connection()
    }
    data class Unknown(val throwable: Throwable) : ErrorModel
}

fun Throwable.toError(): ErrorModel {
    return when (this) {
        is ApiException -> ErrorModel.ApiError(this)
        is SocketTimeoutException -> ErrorModel.Connection.Timeout
        is UnknownHostException -> ErrorModel.Connection.UnknownHost
        is IOException -> ErrorModel.Connection.IOError
        else -> ErrorModel.Unknown(this)
    }
}
