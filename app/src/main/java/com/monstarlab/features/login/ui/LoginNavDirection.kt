package com.monstarlab.features.login.ui

import com.monstarlab.core.ui.navigation.Direction
import com.monstarlab.core.ui.navigation.RouteSpec

object LoginNavDirection : RouteSpec(), Direction {
    override val name: String = "login"

    override val route: String = name
}