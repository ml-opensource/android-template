package com.monstarlab.features.resources.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.monstarlab.core.persistence.SharedPreferenceDataStore
import com.monstarlab.features.resources.domain.Resource
import javax.inject.Inject

class ResourcePreferenceStore @Inject constructor(
    dataStore: DataStore<Preferences>
) : SharedPreferenceDataStore<Resource>(dataStore, Resource.serializer())
