package com.monstarlab.mock

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MockFlows {

    fun mockString(): Flow<String> = flow {
        delay(2000)
        emit("Hello world from Flow")
    }

    fun mockFlag(): Flow<Boolean> = flow {
        delay(1000)
        emit(false)
        delay(2000)
        emit(true)
    }

}