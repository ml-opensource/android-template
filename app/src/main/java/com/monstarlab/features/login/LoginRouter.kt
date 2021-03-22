package com.monstarlab.features.login

import com.monstarlab.core.navigation.NavigationModel
import com.monstarlab.core.navigation.Router
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class LoginRouter @Inject constructor() : Router<LoginViewModel.LoginNavigation> {
    override val navigationState: Channel<NavigationModel> = Channel(Channel.BUFFERED)

    override suspend fun route(navigation: LoginViewModel.LoginNavigation) {
        val navigationModel = when (navigation) {
            is LoginViewModel.LoginNavigation.Resource -> NavigationModel(
                LoginFragmentDirections.actionLoginToResource(navigation.sampleMessage)
            )
        }

        navigationState.send(navigationModel)
    }

}
