package com.monstarlab.features.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.monstarlab.core.ui.navigation.extensions.composable
import com.monstarlab.features.login.ui.LoginNavDirection
import com.monstarlab.features.login.ui.LoginRoute
import com.monstarlab.features.login.ui.rememberLoginCoordinator
import com.monstarlab.features.resources.ui.ResourcesNavDirection
import com.monstarlab.features.resources.ui.ResourcesRoute
import com.monstarlab.features.resources.ui.rememberResourcesCoordinator


@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = LoginNavDirection.name,
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        composable(LoginNavDirection) {
            LoginRoute(coordinator = rememberLoginCoordinator(navController))
        }
        
        composable(ResourcesNavDirection) {
            ResourcesRoute(coordinator = rememberResourcesCoordinator())
        }
    }
}