package com.monstarlab.features.resources.injection

import com.monstarlab.features.resources.data.repository.ResourceRepositoryImpl
import com.monstarlab.features.resources.domain.repository.ResourceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindResourceRepository(resourceRepository: ResourceRepositoryImpl) : ResourceRepository
}