package com.monstarlab.arch.data

import android.content.SharedPreferences
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception

abstract class SharedPreferenceDataStore<T> constructor(
    private val sharedPreferences: SharedPreferences
): DataSource<T> {

    private val key = this.javaClass.simpleName

    override fun getAll(): List<T> {
        return try {
            val json = sharedPreferences.getString(key, "") ?: ""
            val entries = Json.decodeFromString<List<T>>(json)
            entries
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun add(item: T) {
        val list = getAll().toMutableList()
        list.add(item)
        addAll(list)
    }

    override fun addAll(items: List<T>) {
        try {
            val json = Json.encodeToString(items)
            sharedPreferences.edit().putString(key, json).apply()
        } catch (e: Exception) {

        }
    }

    override fun clear() {
        sharedPreferences.edit().remove(key).apply()
    }
}