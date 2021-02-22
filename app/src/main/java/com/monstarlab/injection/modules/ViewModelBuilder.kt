package com.monstarlab.injection.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindViewModelFactory(factory: com.monstarlab.injection.modules.DaggerViewModelFactory): ViewModelProvider.Factory
}
