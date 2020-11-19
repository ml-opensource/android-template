package com.monstarlab.injection.modules

import android.content.Context
import com.monstarlab.App
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {

    companion object {
        @Provides
        fun provideContext(application: App): Context = application.applicationContext
    }
}