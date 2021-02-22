package com.monstarlab.injection.builders

import androidx.lifecycle.ViewModel
import com.monstarlab.features.resources.ResourceFragment
import com.monstarlab.features.resources.ResourceViewModel
import com.monstarlab.injection.modules.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ResourceBuilder {
    @ContributesAndroidInjector
    abstract fun sampleFragment(): ResourceFragment

    @Binds
    @IntoMap
    @ViewModelKey(ResourceViewModel::class)
    internal abstract fun bindSampleViewModel(viewModel: ResourceViewModel): ViewModel
}
