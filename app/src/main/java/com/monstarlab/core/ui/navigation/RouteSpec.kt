package com.monstarlab.core.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavDeepLink

/**
 * Fully describes the Route that can be used as part of the Navigation Graph
 */
abstract class RouteSpec {

    /**
     * Name of the Route
     */
    abstract val name: String

    /**
     * Arguments supported by the NavHost
     */
    val arguments: List<NamedNavArgument> = emptyList()

    /**
     * List of the deepLinks supported by this route
     */
    val deepLinks: List<NavDeepLink> = emptyList()
}