package com.monstarlab.features.user.data.persistence

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.monstarlab.core.persistence.SinglePreferenceDataStore
import com.monstarlab.features.user.domain.model.User
import javax.inject.Inject

class UserPreferenceStore @Inject constructor(
    dataStore: DataStore<Preferences>,
) : SinglePreferenceDataStore<User>(dataStore, User.serializer())
