package com.monstarlab.injection.builders

import androidx.lifecycle.ViewModel
import com.monstarlab.features.sample.SampleFragment
import com.monstarlab.features.sample.SampleViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dk.nodes.template.injection.modules.ViewModelKey

@Module
internal abstract class SampleBuilder {
    @ContributesAndroidInjector
    abstract fun sampleFragment(): SampleFragment

    @Binds
    @IntoMap
    @ViewModelKey(SampleViewModel::class)
    internal abstract fun bindSampleViewModel(viewModel: SampleViewModel): ViewModel
}