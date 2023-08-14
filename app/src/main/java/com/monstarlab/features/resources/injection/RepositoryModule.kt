package com.monstarlab.features.resources.injection

import com.monstarlab.features.resources.domain.repository.ResourceRepository
import com.monstarlab.features.resources.domain.repository.ResourceRepositoryImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    fun bindResourceRepository(resourceRepository: ResourceRepositoryImpl) : ResourceRepository
}