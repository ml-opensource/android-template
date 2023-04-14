package com.monstarlab.core.network.errorhandling

import java.io.IOException

/**
 * Represents the error that happened while communicating with REST API
 * @param code - HTTP code of the response
 * @param displayableMessage - message that can displayed to the user
 */
data class ApiException(
    val code: Int,
    val displayableMessage: String,
) : IOException(displayableMessage)
