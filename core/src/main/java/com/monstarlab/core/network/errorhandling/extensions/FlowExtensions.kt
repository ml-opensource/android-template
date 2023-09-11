package com.monstarlab.core.network.errorhandling.extensions

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration

fun tickerFlow(interval: Duration, initialDelay: Duration = Duration.ZERO): Flow<Unit> {
    return flow {
        delay(initialDelay)
        while (true) {
            emit(Unit)
            delay(interval)
        }
    }
}
