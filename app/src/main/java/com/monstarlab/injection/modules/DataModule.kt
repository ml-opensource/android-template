package com.monstarlab.injection.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.applicationContext.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
}
