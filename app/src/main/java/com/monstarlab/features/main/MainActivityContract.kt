package com.monstarlab.features.main

import dk.nodes.nstack.kotlin.models.AppOpenData

data class MainActivityState(
    val showSplash: Boolean = true,
    val nstackData: AppOpenData? = null
)
