package com.monstarlab.core.usecases.blog

import com.monstarlab.arch.extensions.UseCaseResult
import com.monstarlab.arch.extensions.useCaseFlow
import com.monstarlab.core.data.repositories.PostRepository
import com.monstarlab.core.domain.model.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    fun getPosts(): Flow<UseCaseResult<List<Post>>> = useCaseFlow {
        postRepository.getPosts()
    }
}
