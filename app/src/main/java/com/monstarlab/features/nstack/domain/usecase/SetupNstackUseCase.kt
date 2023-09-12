package com.monstarlab.features.nstack.domain.usecase

import com.monstarlab.core.injection.IoDispatcher
import com.monstarlab.core.extensions.suspendRunCatching
import dk.nodes.nstack.kotlin.NStack
import dk.nodes.nstack.kotlin.models.AppOpenData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception
import kotlin.Result
import dk.nodes.nstack.kotlin.models.Result as NstackResult

class SetupNstackUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
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