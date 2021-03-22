package com.monstarlab.core.navigation

import kotlinx.coroutines.channels.Channel

/**
 * Router is implemented to have an easily testable navigation logic holder,
 * to seperate navigation logic from easily swellable viewModels and to make viewModels reusable
 * with different view layers (which we theoretically need but not actually do).
 */
interface Router<T> {

    /**
     * Reason for channel usage instead of flow:
     * https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055
     */
    val navigationState: Channel<NavigationModel>

    suspend fun route(navigation: T)
}
// todo check for jetpack compose