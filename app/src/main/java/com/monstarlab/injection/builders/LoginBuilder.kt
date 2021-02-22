package com.monstarlab.injection.builders

import androidx.lifecycle.ViewModel
import com.monstarlab.features.login.LoginFragment
import com.monstarlab.features.login.LoginViewModel
import com.monstarlab.injection.modules.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class LoginBuilder {
    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}
