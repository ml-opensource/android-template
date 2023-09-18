package com.monstarlab.injection

import com.monstarlab.BuildConfig
import com.monstarlab.core.network.NetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideBaseUrl(): NetworkConfig {
        return NetworkConfig(BuildConfig.API_URL)
    }
}
