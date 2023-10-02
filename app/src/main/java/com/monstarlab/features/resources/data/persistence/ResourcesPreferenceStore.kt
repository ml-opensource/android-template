package com.monstarlab.features.resources.data.persistence

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.monstarlab.core.persistence.ListPreferenceDataStore
import com.monstarlab.features.resources.domain.model.Resource
import javax.inject.Inject

class ResourcesPreferenceStore @Inject constructor(
    dataStore: DataStore<Preferences>,
) : ListPreferenceDataStore<Resource>(dataStore, Resource.serializer())
