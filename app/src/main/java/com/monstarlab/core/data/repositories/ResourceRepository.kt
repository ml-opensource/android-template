package com.monstarlab.core.data.repositories

import com.monstarlab.arch.data.Repository
import com.monstarlab.arch.extensions.RepositoryResult
import com.monstarlab.arch.extensions.onError
import com.monstarlab.arch.extensions.onSuccess
import com.monstarlab.arch.extensions.toResultAndMap
import com.monstarlab.core.data.network.Api
import com.monstarlab.core.data.network.dtos.toEntity
import com.monstarlab.core.data.storage.ResourcePreferenceStore
import com.monstarlab.core.domain.model.Resource
import javax.inject.Inject

class ResourceRepository @Inject constructor(
    private val api: Api,
    private val resourcePreferenceStore: ResourcePreferenceStore
): Repository() {

    suspend fun getResources(): RepositoryResult<List<Resource>> {
        onShouldFetch {
            val result = api.getResources().toResultAndMap { response -> response.data.map { it.toEntity() } }

            result.onSuccess {
                resourcePreferenceStore.addAll(it)
            }

            result.onError { return RepositoryResult.Error(it) }
        }

        return RepositoryResult.Success(resourcePreferenceStore.getAll())
    }

}