package com.monstarlab.core.ui.navigation

/**
 * Basic interfaces that defines any type of Direction within application the navigation graph
 *
 */
interface Direction {
    /**
     * Fully qualified route name with the arguments provided as templates
     * @sample "products/{id}?scrollToBottom={scrollToBottom}
     */
    val route: String

}