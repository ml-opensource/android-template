package com.monstarlab.arch.data

interface SingleDataSource<T> {
    fun get(): T?
    fun add(item: T)
    fun clear()
}
