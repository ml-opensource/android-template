package com.monstarlab.core.data.storage

import android.content.SharedPreferences
import com.monstarlab.arch.data.SharedPreferenceDataStore
import com.monstarlab.core.domain.model.Post
import javax.inject.Inject

class PostPreferenceStore @Inject constructor(
    sharedPreferences: SharedPreferences
) : SharedPreferenceDataStore<Post>(sharedPreferences, Post.serializer())
