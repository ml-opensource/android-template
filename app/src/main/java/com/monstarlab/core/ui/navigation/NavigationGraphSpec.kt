package com.monstarlab.core.ui.navigation

interface NavigationGraphSpec: Direction {
    val name: String

    override val route: String
        get() = name
}