package com.monstarlab.core.data.storage

import android.content.SharedPreferences
import com.monstarlab.arch.data.SharedPreferenceDataStore
import com.monstarlab.core.domain.model.Post
import com.monstarlab.core.domain.model.Resource
import javax.inject.Inject

class ResourcePreferenceStore @Inject constructor(
    sharedPreferences: SharedPreferences
): SharedPreferenceDataStore<Resource>(sharedPreferences, Resource.serializer()) {

}