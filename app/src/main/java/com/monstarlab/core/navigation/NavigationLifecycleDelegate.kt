package com.monstarlab.core.navigation

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.ref.WeakReference


/**
 * Lifecycle delegate that implements navigation with Navigation Component by observing
 * navigation state of injected NavigationOwner of fragment.
 */
class NavigationLifecycleDelegate(
    private val fragmentReference: WeakReference<Fragment>,
    private val navigationOwner: NavigationOwner<*>
): LifecycleObserver {

    // TODO A provider for navigation controller can be injected instead of fragment.
    private val fragment: Fragment?
        get() = fragmentReference.get()

    private var job: Job? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun observeNavigationOwner() {
        fragment?.let { fragment ->

            job = navigationOwner.navigationState
                .onEach { model ->
                    if (model.popBackStack) {
                        model.actionId?.let { destinationId ->
                            fragment.findNavController().popBackStack(destinationId, true)
                        } ?: fragment.findNavController().popBackStack()
                    } else {
                        model.actionId?.let {
                            fragment.findNavController().navigate(it, model.arguments, model.navigationOptions, model.extras)
                        }
                    }
                }
                .launchIn(fragment.lifecycleScope)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopObservingNavigationOwner() {
        job?.cancel()
        job = null
    }

}
