package com.monstarlab.features.resources.domain.repository

import com.monstarlab.features.resources.domain.model.Resource

interface ResourceRepository {
    suspend fun get(): List<Resource>
}


