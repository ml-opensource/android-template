package com.monstarlab.core.ui.navigation.extensions

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.monstarlab.core.ui.navigation.RouteSpec


fun NavGraphBuilder.composable(
    spec: RouteSpec,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit),
) {
    composable(
        route = spec.name,
        arguments = spec.arguments,
        deepLinks = spec.deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        content = content
    )
}
