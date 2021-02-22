package com.monstarlab.core.usecases.resources

import com.monstarlab.arch.extensions.useCaseFlow
import com.monstarlab.core.data.repositories.ResourceRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetResourcesUseCase @Inject constructor(
    private val resourceRepository: ResourceRepository
) {

    fun getResources() = useCaseFlow {
        delay(2000)
        resourceRepository.getResources()
    }
}
