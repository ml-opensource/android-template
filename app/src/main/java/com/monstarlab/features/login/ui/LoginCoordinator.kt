package com.monstarlab.features.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.monstarlab.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class LoginCoordinator(
    val viewModel: LoginViewModel,
    val navController: NavController,
    val scope: CoroutineScope
) {
    val screenStateFlow = viewModel.stateFlow

    init {
        viewModel.stateFlow.map { it.isLoggedIn }
            .distinctUntilChanged()
            .filter { it }
            .onEach {
                navController.navigate(
                    R.id.resourceFragment,
                    navOptions = NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build(),
                    args = null
                )
            }
            .launchIn(scope)
    }
}

@Composable
fun rememberLoginCoordinator(
    viewModel: LoginViewModel,
    navController: NavController,
    scope: CoroutineScope = rememberCoroutineScope()
): LoginCoordinator {
    return remember(viewModel, navController, scope) {
        LoginCoordinator(
            viewModel = viewModel,
            navController = navController,
            scope = scope
        )
    }
}
