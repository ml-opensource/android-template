package com.monstarlab.core.usecases.resources

import com.monstarlab.arch.extensions.Result
import com.monstarlab.arch.extensions.safeFlow
import com.monstarlab.core.data.repositories.ResourceRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetResourcesUseCase @Inject constructor(
    private val resourceRepository: ResourceRepository
) {

    fun getResources() = flow {
        delay(2000)
        emit(Result { resourceRepository.getResources() })
    }

}