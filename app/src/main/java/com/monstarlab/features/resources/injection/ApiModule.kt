package com.monstarlab.features.resources.injection

import com.monstarlab.features.resources.data.api.ResourcesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ResourcesApi {
        return retrofit.create(ResourcesApi::class.java)
    }
}
