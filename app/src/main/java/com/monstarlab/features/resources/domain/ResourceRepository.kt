package com.monstarlab.features.resources.domain

import com.monstarlab.core.extensions.mapSuccess
import com.monstarlab.core.persistence.Repository
import com.monstarlab.features.resources.data.api.ResourcesApi
import com.monstarlab.features.resources.data.api.dtos.toResource
import com.monstarlab.features.resources.data.storage.ResourcePreferenceStore
import javax.inject.Inject

class ResourceRepository @Inject constructor(
    private val api: ResourcesApi,
    private val resourcePreferenceStore: ResourcePreferenceStore
) : Repository() {

    suspend fun getResources(): List<Resource> {
        return api.getResources()
            .mapSuccess { response -> response.data.map { it.toResource() } }
            .also { resourcePreferenceStore.addAll(it) }
    }
}
