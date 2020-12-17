package com.monstarlab.core.data.storage

import android.content.SharedPreferences
import com.monstarlab.arch.data.SharedPreferenceDataStore
import com.monstarlab.arch.data.SingleSharedPreferenceDataStore
import com.monstarlab.core.domain.model.Post
import com.monstarlab.core.domain.model.User
import javax.inject.Inject

class UserPreferenceStore @Inject constructor(
        sharedPreferences: SharedPreferences
): SingleSharedPreferenceDataStore<User>(sharedPreferences, User.serializer()) {
}
