package com.monstarlab.arch.data

import java.util.concurrent.TimeUnit

abstract class Repository constructor(
    private val expirationInSeconds: Long = 30
) {

    private var lastFetch = 0L

    protected suspend inline fun onShouldFetch(block: suspend () -> Unit) {
        if(shouldFetch) block.invoke()
    }

    protected val shouldFetch: Boolean
        get() {
            val shouldFetch = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - lastFetch) >= expirationInSeconds
            // Automatically update this value, since we're running API code in an if() and most likely updating local store
            if(shouldFetch) {
                lastFetch = System.currentTimeMillis()
            }
            return shouldFetch
        }

}