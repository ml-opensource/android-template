package com.monstarlab.arch.data

import java.util.concurrent.TimeUnit

abstract class Repository constructor(
    private val expirationInSeconds: Long = 30
) {

    private var lastFetch = 0L

    protected val shouldFetch: Boolean
        get() {
            return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - lastFetch) >= expirationInSeconds
        }

    protected fun fetched() {
        lastFetch = System.currentTimeMillis()
    }


}