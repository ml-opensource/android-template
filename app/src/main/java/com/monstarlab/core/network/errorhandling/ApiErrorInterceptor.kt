package com.monstarlab.core.network.errorhandling

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import timber.log.Timber
import javax.inject.Inject

/**
 * Intercepts API errors and converts them into [ApiException] by decoding the Error response body
 * In case serialization fails the response will be delivered as is
 *
 */
class ApiErrorInterceptor @Inject constructor(
    private val json: Json
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.isSuccessful) {
            return response
        }
        val errorBody = response.body?.string()
        val errorDtoResult = runCatching {
            json.decodeFromString<ApiErrorDTO>(requireNotNull(errorBody))
        }

        if (errorDtoResult.isSuccess) {
            throw errorDtoResult.getOrThrow().toApiError(response.code)
        } else {
            val newErrorBody = errorBody?.toResponseBody("application/json".toMediaType())
            Timber.e("Failed to deserialize error body: ${errorDtoResult.exceptionOrNull()}")
            return response.newBuilder().body(newErrorBody).build()
        }
    }
}
