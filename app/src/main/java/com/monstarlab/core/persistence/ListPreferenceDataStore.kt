package com.monstarlab.core.persistence

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

abstract class ListPreferenceDataStore<T> constructor(
    private val dataStore: DataStore<Preferences>,
    private val serializer: KSerializer<T>,
) : ListDataSource<T> {

    private val key = stringPreferencesKey(this.javaClass.simpleName)

    /**
     * @return the stored collection of objects or an empty list, if no object was stored
     */
    override suspend fun load(): List<T> {
        return try {
            val json = dataStore.data.map { it[key] ?: "" }.first()
            Json.decodeFromString(ListSerializer(serializer), json)
        } catch (e: SerializationException) {
            emptyList()
        }
    }

    /**
     * Add a single [item] to the collection of stored elements
     */
    override suspend fun add(item: T) {
        save(load().plus(item))
    }

    /**
     * Write [items] to storage, replacing any current ones
     */
    override suspend fun save(items: List<T>) {
        val json = Json.encodeToString(ListSerializer(serializer), items)
        dataStore.edit {
            it[key] = json
        }
    }

    /**
     * Remove the storage
     */
    override suspend fun clear() {
        dataStore.edit {
            it.remove(key)
        }
    }
}
