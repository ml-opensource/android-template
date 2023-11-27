package com.monstarlab.features.user.data.persistence

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.monstarlab.core.persistence.SingleSharedPreferenceDataStore
import com.monstarlab.features.user.domain.model.User
import javax.inject.Inject

class UserPreferenceStore @Inject constructor(
    dataStore: DataStore<Preferences>
) : SingleSharedPreferenceDataStore<User>(dataStore, User.serializer())
