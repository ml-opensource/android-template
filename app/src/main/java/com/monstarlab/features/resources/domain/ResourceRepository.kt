package com.monstarlab.features.resources.domain

import com.monstarlab.core.persistence.Repository
import com.monstarlab.core.extensions.mapSuccess
import com.monstarlab.features.resources.data.toEntity
import com.monstarlab.features.resources.data.ResourcePreferenceStore
import com.monstarlab.features.resources.data.ResourcesApi
import javax.inject.Inject

class ResourceRepository @Inject constructor(
    private val api: ResourcesApi,
    private val resourcePreferenceStore: ResourcePreferenceStore
) : Repository() {

    suspend fun getResources(): List<Resource> {
        return api.getResources()
            .mapSuccess { response -> response.data.map { it.toEntity() } }
            .also { resourcePreferenceStore.addAll(it) }
    }
}
