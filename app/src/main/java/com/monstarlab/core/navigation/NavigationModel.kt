package com.monstarlab.core.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

data class NavigationModel(
    @IdRes val actionId: Int? = null,
    val arguments: Bundle? = null,
    val popBackStack: Boolean = false,
    val navigationOptions: NavOptions? = null,
    val extras: Navigator.Extras? = null
) {
    constructor(navDirections: NavDirections, navigationOptions: NavOptions? = null, extras: Navigator.Extras? = null) : this(
        actionId = navDirections.actionId,
        arguments = navDirections.arguments,
        navigationOptions = navigationOptions,
        extras = extras
    )
}
