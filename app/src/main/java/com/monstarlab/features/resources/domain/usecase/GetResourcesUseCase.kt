package com.monstarlab.features.resources.domain.usecase

import com.monstarlab.core.network.errorhandling.extensions.suspendRunCatching
import com.monstarlab.features.resources.domain.repository.ResourceRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetResourcesUseCase @Inject constructor(
    private val resourceRepository: ResourceRepository,
) {

    suspend operator fun invoke() = suspendRunCatching {
        delay(2000)
        resourceRepository.get()
    }
}
