package com.monstarlab.core.ui.navigation.extensions

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.PopUpToBuilder
import com.monstarlab.core.ui.navigation.Direction
import com.monstarlab.core.ui.navigation.NavigationGraphSpec
import com.monstarlab.core.ui.navigation.RouteSpec


fun NavController.navigateTo(
    direction: Direction,
    navOptions: NavOptions? = null,
    naxExtras: Navigator.Extras
) {
    navigate(
        route = direction.route,
        navOptions = navOptions,
        navigatorExtras = naxExtras
    )
}

fun NavController.navigateTo(
    direction: Direction,
    builder: NavOptionsBuilder.() -> Unit = {}
) = navigate(
    route = direction.route,
    builder = builder
)

fun NavOptionsBuilder.popUpTo(
    route: RouteSpec,
    popUpToBuilder: PopUpToBuilder.() -> Unit = {}
) = popUpTo(
    route.name,
    popUpToBuilder
)


fun NavOptionsBuilder.popUpTo(
    graph: NavigationGraphSpec,
    popUpToBuilder: PopUpToBuilder.() -> Unit = {}
) = popUpTo(
    graph.name,
    popUpToBuilder
)
