package com.monstarlab.features.resources.domain.usecase

import com.monstarlab.core.extensions.suspendRunCatching
import com.monstarlab.features.resources.domain.repository.ResourceRepository
import javax.inject.Inject
import kotlinx.coroutines.delay

class GetResourcesUseCase @Inject constructor(
    private val resourceRepository: ResourceRepository,
) {

    suspend operator fun invoke() = suspendRunCatching {
        delay(2000)
        resourceRepository.get()
    }
}
