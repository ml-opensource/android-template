package com.monstarlab.arch.data

import android.content.SharedPreferences
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

abstract class SingleSharedPreferenceDataStore<T> constructor(
    private val sharedPreferences: SharedPreferences,
    private val serializer: KSerializer<T>
) : SingleDataSource<T> {

    private val key = this.javaClass.simpleName

    override fun get(): T? {
        return try {
            val json = sharedPreferences.getString(key, "") ?: ""
            val entries = Json.decodeFromString(serializer, json)
            entries
        } catch (e: Exception) {
            null
        }
    }

    override fun add(item: T) {
        try {
            val json = Json.encodeToString(serializer, item)
            sharedPreferences.edit().putString(key, json).apply()
        } catch (e: Exception) {
        }
    }

    override fun clear() {
        sharedPreferences.edit().remove(key).apply()
    }
}
