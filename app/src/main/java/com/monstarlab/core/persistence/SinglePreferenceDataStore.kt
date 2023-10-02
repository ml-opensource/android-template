package com.monstarlab.core.persistence

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import timber.log.Timber

abstract class SinglePreferenceDataStore<T> constructor(
    private val dataStore: DataStore<Preferences>,
    private val serializer: KSerializer<T>,
) : DataSource<T> {

    private val key = stringPreferencesKey(this.javaClass.simpleName)

    /**
     * @return the stored object or null, if no object was stored
     */
    override suspend fun load(): T? {
        return try {
            val json = dataStore.data.map { it[key] ?: "" }.first()
            Json.decodeFromString(serializer, json)
        } catch (e: SerializationException) {
            null
        }
    }

    /**
     * Write [item] to storage, replacing any existing ones
     */
    override suspend fun save(item: T) {
        try {
            val json = Json.encodeToString(serializer, item)
            dataStore.edit {
                it[key] = json
            }
        } catch (e: SerializationException) {
            Timber.e(e)
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
