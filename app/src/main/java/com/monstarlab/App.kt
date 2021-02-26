package com.monstarlab

import android.app.Application
import com.monstarlab.features.nstack.Translation
import dagger.hilt.android.HiltAndroidApp
import dk.nodes.nstack.kotlin.NStack
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        NStack.translationClass = Translation::class.java
        NStack.init(this, BuildConfig.DEBUG)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
