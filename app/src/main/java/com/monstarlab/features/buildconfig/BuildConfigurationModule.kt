package com.monstarlab.features.buildconfig

import com.monstarlab.core.config.BuildConfiguration
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BuildConfigurationModule {

    @Binds
    @Singleton
    fun bindBuildConfiguration(appBuildConfiguration: AppBuildConfiguration): BuildConfiguration
}
