package com.monstarlab.features.resources.domain

import kotlinx.coroutines.delay
import javax.inject.Inject

class GetResourcesUseCase @Inject constructor(
    private val resourceRepository: ResourceRepository
) {

    suspend operator fun invoke() = runCatching {
        delay(2000)
        resourceRepository.getResources()
    }
}
