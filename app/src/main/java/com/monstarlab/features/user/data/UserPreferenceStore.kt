package com.monstarlab.features.user.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.monstarlab.arch.data.SingleSharedPreferenceDataStore
import com.monstarlab.features.user.domain.User
import javax.inject.Inject

class UserPreferenceStore @Inject constructor(
    dataStore: DataStore<Preferences>
) : SingleSharedPreferenceDataStore<User>(dataStore, User.serializer())
