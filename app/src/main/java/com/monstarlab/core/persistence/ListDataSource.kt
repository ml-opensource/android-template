package com.monstarlab.core.persistence

interface ListDataSource<T> : DataSource<List<T>> {
    override suspend fun save(items: List<T>)
    suspend fun add(item: T)
}
