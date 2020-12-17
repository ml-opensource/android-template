package com.monstarlab.injection.modules

import com.monstarlab.injection.builders.LoginBuilder
import com.monstarlab.injection.builders.MainActivityBuilder
import com.monstarlab.injection.builders.ResourceBuilder
import dagger.Module

@Module(includes = [
    MainActivityBuilder::class,
    ResourceBuilder::class,
    LoginBuilder::class
])
class PresentationModule