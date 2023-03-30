package com.monstarlab.features.nstack.domain.usecase

import com.monstarlab.core.extensions.suspendRunCatching
import com.monstarlab.core.injection.IoDispatcher
import dk.nodes.nstack.kotlin.NStack
import dk.nodes.nstack.kotlin.models.AppOpenData
import kotlinx.coroutines.*
import javax.inject.Inject

class SetupNstackUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(): Result<AppOpenData> = suspendRunCatching {
        withContext(ioDispatcher) {
            when (val result = NStack.appOpen()) {
                is dk.nodes.nstack.kotlin.models.Result.Success -> result.value.data
                is dk.nodes.nstack.kotlin.models.Result.Error -> throw Exception("Failed to appOpen NStack")
            }
        }
    }

}