package com.monstarlab.features.nstack.domain.usecase

import com.monstarlab.core.extensions.suspendRunCatching
import com.monstarlab.core.injection.IoDispatcher
import dk.nodes.nstack.kotlin.NStack
import dk.nodes.nstack.kotlin.models.AppOpenData
import dk.nodes.nstack.kotlin.models.Result as NstackResult
import javax.inject.Inject
import kotlin.Exception
import kotlin.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SetupNstackUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Result<AppOpenData> = suspendRunCatching {
        withContext(ioDispatcher) {
            when (val result = NStack.appOpen()) {
                is NstackResult.Success -> result.value.data
                is NstackResult.Error -> throw Exception("Failed to appOpen NStack")
            }
        }
    }
}
