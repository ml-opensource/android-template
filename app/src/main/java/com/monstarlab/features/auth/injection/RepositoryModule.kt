package com.monstarlab.features.auth.injection

import com.monstarlab.features.auth.data.repository.AuthRepositoryImpl
import com.monstarlab.features.auth.domain.repository.AuthRepository
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
    fun bindAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository
}
