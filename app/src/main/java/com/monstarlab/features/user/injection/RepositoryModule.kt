package com.monstarlab.features.user.injection

import com.monstarlab.features.user.data.repository.UserRepositoryImpl
import com.monstarlab.features.user.domain.repository.UserRepository
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
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository
}
