package com.monstarlab.core.usecases.blog

import com.monstarlab.arch.extensions.SealedResult
import com.monstarlab.core.data.repositories.PostRepository
import com.monstarlab.core.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    fun getPosts(): Flow<SealedResult<List<Post>>> = flow {
        emit(SealedResult { postRepository.getPosts() })
    }
}