package com.monstarlab.injection.builders

import com.monstarlab.features.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainActivityBuilder {

    @ContributesAndroidInjector(modules = [])
    internal abstract fun mainActivity(): MainActivity
}
