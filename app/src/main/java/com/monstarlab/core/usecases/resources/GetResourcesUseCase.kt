package com.monstarlab.core.usecases.resources

import com.monstarlab.arch.extensions.SealedResult
import com.monstarlab.core.data.repositories.ResourceRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetResourcesUseCase @Inject constructor(
    private val resourceRepository: ResourceRepository
) {

    fun getResources() = flow {
        delay(2000)
        emit(SealedResult { resourceRepository.getResources() })
    }

}