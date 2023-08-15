package com.monstarlab.features.resources.ui

import com.monstarlab.core.ui.navigation.Direction
import com.monstarlab.core.ui.navigation.RouteSpec

object ResourcesNavDirection : RouteSpec(), Direction {
    override val name: String = "resources"
    override val route: String = name
}