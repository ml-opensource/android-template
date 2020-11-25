package com.monstarlab.injection.builders

import androidx.lifecycle.ViewModel
import com.monstarlab.features.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainActivityBuilder {

    @ContributesAndroidInjector(modules = [])
    internal abstract fun mainActivity(): MainActivity
}