package com.monstarlab.core.persistence

interface SingleDataSource<T> {
    suspend fun get(): T?
    suspend fun add(item: T)
    suspend fun clear()
}
