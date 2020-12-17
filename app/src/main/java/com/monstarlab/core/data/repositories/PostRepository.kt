package com.monstarlab.core.data.repositories

import com.monstarlab.arch.data.*
import com.monstarlab.arch.extensions.RepositoryResult
import com.monstarlab.arch.extensions.isError
import com.monstarlab.arch.extensions.onSuccess
import com.monstarlab.arch.extensions.toResultAndMap
import com.monstarlab.core.data.network.dtos.toEntity
import com.monstarlab.core.data.network.Api
import com.monstarlab.core.data.storage.PostPreferenceStore
import com.monstarlab.core.domain.model.Post
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: Api,
    private val postPreferenceStore: PostPreferenceStore
): Repository() {

    suspend fun getPosts(): RepositoryResult<List<Post>> {
        onShouldFetch {
            val result = api.getPosts().toResultAndMap { list -> list.map { it.toEntity() } }

            result.onSuccess { list ->
                postPreferenceStore.addAll(list)
            }

            if(result.isError()) return result
        }
        return RepositoryResult.Success(postPreferenceStore.getAll())
    }

}