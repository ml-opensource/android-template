package com.monstarlab.injection.components

import com.monstarlab.App
import com.monstarlab.injection.modules.AppModule
import com.monstarlab.injection.modules.DataModule
import com.monstarlab.injection.modules.InteractorModule
import com.monstarlab.injection.modules.PresentationModule
import com.monstarlab.injection.modules.RestModule
import com.monstarlab.injection.modules.ViewModelBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelBuilder::class,
        PresentationModule::class,
        AppModule::class,
        InteractorModule::class,
        RestModule::class,
        DataModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}
