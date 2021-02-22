package com.monstarlab.arch.data

interface DataSource<T> {
    fun getAll(): List<T>
    fun add(item: T)
    fun addAll(items: List<T>)
    fun clear()
}
