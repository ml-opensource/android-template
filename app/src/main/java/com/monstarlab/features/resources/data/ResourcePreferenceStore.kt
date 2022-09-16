package com.monstarlab.features.resources.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.monstarlab.arch.data.SharedPreferenceDataStore
import com.monstarlab.features.resources.domain.Resource
import javax.inject.Inject

class ResourcePreferenceStore @Inject constructor(
    dataStore: DataStore<Preferences>
) : SharedPreferenceDataStore<Resource>(dataStore, Resource.serializer())
