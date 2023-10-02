package com.monstarlab.features.resources.data.repository

import com.monstarlab.features.resources.data.api.ResourcesApi
import com.monstarlab.features.resources.data.api.dtos.toResource
import com.monstarlab.features.resources.data.persistence.ResourcesPreferenceStore
import com.monstarlab.features.resources.domain.model.Resource
import com.monstarlab.features.resources.domain.repository.ResourceRepository
import javax.inject.Inject

class ResourceRepositoryImpl @Inject constructor(
    private val api: ResourcesApi,
    private val resourcesPreferenceStore: ResourcesPreferenceStore,
) : ResourceRepository {

    override suspend fun get(): List<Resource> {
        return api.getResources().data.map { it.toResource() }.also {
            resourcesPreferenceStore.save(it)
        }
    }
}
