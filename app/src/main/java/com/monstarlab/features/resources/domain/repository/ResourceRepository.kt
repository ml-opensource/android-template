package com.monstarlab.features.resources.domain.repository

import com.monstarlab.core.persistence.Repository
import com.monstarlab.features.resources.data.api.ResourcesApi
import com.monstarlab.features.resources.data.api.dtos.toResource
import com.monstarlab.features.resources.data.storage.ResourcePreferenceStore
import com.monstarlab.features.resources.domain.model.Resource
import javax.inject.Inject

class ResourceRepository @Inject constructor(
    private val api: ResourcesApi,
    private val resourcePreferenceStore: ResourcePreferenceStore,
) : Repository() {

    suspend fun getResources(): List<Resource> {
        return api.getResources().data.map { it.toResource() }.also {
            resourcePreferenceStore.addAll(it)
        }
    }
}
