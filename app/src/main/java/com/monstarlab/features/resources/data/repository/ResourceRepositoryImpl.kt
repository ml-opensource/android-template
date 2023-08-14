package com.monstarlab.features.resources.data.repository

import com.monstarlab.features.resources.data.api.ResourcesApi
import com.monstarlab.features.resources.data.api.dtos.toResource
import com.monstarlab.features.resources.data.storage.ResourcePreferenceStore
import com.monstarlab.features.resources.domain.model.Resource
import com.monstarlab.features.resources.domain.repository.ResourceRepository
import javax.inject.Inject

class ResourceRepositoryImpl @Inject constructor(
    private val api: ResourcesApi,
    private val resourcePreferenceStore: ResourcePreferenceStore,
) : ResourceRepository {

    override suspend fun get(): List<Resource> {
        return api.getResources().data.map { it.toResource() }.also {
            resourcePreferenceStore.addAll(it)
        }
    }
}