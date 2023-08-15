package com.monstarlab.features.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.monstarlab.R
import com.monstarlab.core.ui.navigation.extensions.navigateTo
import com.monstarlab.core.ui.navigation.extensions.popUpTo
import com.monstarlab.features.resources.ui.ResourcesNavDirection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class LoginCoordinator(
    val viewModel: LoginViewModel,
    val navController: NavController,
    val scope: CoroutineScope,
) {
    val screenStateFlow = viewModel.stateFlow

    init {
        viewModel.stateFlow
            .filter { it.isLoggedIn }
            .onEach {
                navController.navigateTo(ResourcesNavDirection) {
                    popUpTo(LoginNavDirection) {
                        inclusive = true
                    }
                }
            }
            .launchIn(scope)
    }
}

@Composable
fun rememberLoginCoordinator(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
): LoginCoordinator {
    return remember(viewModel, navController, scope) {
        LoginCoordinator(
            viewModel = viewModel,
            navController = navController,
            scope = scope
        )
    }
}
