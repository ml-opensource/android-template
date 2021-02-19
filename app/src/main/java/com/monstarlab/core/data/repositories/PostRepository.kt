package com.monstarlab.core.data.repositories

import com.monstarlab.arch.data.Repository
import com.monstarlab.core.data.mappers.toEntity
import com.monstarlab.core.data.network.Api
import com.monstarlab.core.data.storage.PostPreferenceStore
import com.monstarlab.core.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: Api,
    private val postPreferenceStore: PostPreferenceStore
): Repository() {

    fun getPosts(): Flow<List<Post>> = flow {
        if(shouldFetch) {
            val response = api.getPosts()
            if(!response.isSuccessful) {
                emit(emptyList<Post>())
            }
            val entries = response.body()?.map { it.toEntity() }
            fetched()
            //postPreferenceStore.addAll(entries)
            //emit(entries)
        } else {
            emit(postPreferenceStore.getAll())
        }
    }

}