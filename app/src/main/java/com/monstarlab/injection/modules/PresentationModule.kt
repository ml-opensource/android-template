package com.monstarlab.injection.modules

import com.monstarlab.injection.builders.MainActivityBuilder
import com.monstarlab.injection.builders.SampleBuilder
import dagger.Module

@Module(includes = [
    MainActivityBuilder::class,
    SampleBuilder::class
])
class PresentationModule