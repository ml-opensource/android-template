package com.monstarlab.core.domain.error

sealed class ErrorModel {

    sealed class Http: ErrorModel() {
        object BadRequest: Http()
        object Unauthorized: Http()
        object Forbidden: Http()
        object NotFound: Http()
        object MethodNotAllowed: Http()

        object ServerError: Http()

        data class Custom(
                val code: Int?,
                val message: String?,
                val errorBody: String?
        ): Http()
    }

    sealed class Connection: ErrorModel() {
        object Timeout: Connection()
        object IOError: Connection()
        object UnknownHost: Connection()
    }

    object Unknown: ErrorModel()

}