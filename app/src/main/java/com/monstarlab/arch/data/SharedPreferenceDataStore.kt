package com.monstarlab.arch.data

import android.content.SharedPreferences
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

abstract class SharedPreferenceDataStore<T> constructor(
    private val sharedPreferences: SharedPreferences,
    private val serializer: KSerializer<T>
): DataSource<T> {

    private val key = this.javaClass.simpleName

    override fun getAll(): List<T> {
        val json = sharedPreferences.getString(key, "") ?: ""
        return Json.decodeFromString(ListSerializer(serializer), json)
    }

    override fun add(item: T) {
        val list = getAll().toMutableList()
        list.add(item)
        addAll(list)
    }

    override fun addAll(items: List<T>) {
        val json = Json.encodeToString(ListSerializer(serializer), items)
        sharedPreferences.edit().putString(key, json).apply()
    }

    override fun clear() {
        sharedPreferences.edit().remove(key).apply()
    }
}