package com.monstarlab.core.extensions

inline fun <reified T> Any.castAs(): T {
    return this as T
}

inline fun <reified T> Any.safeCastAs(): T? {
    return this as? T
}
