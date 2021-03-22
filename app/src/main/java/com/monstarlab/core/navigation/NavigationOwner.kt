package com.monstarlab.core.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface NavigationOwner<T : Router<*>> {

    val navigationState: Flow<NavigationModel>
        get() = router.navigationState.receiveAsFlow()

    val router: T

}
