package com.monstarlab.core.persistence

interface DataSource<T> {
    suspend fun load(): T?
    suspend fun save(item: T)
    suspend fun clear()
}
