package com.monstarlab.core.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlinx.coroutines.delay

fun tickerFlow(interval: Duration, initialDelay: Duration = interval) : Flow<Unit> {
    return flow {
        delay(initialDelay)
        while(true) {
            emit(Unit)
            delay(interval)
        }
    }
}
