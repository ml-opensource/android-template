package com.monstarlab.core.config

interface BuildConfiguration {
    val flavour: AppFlavour
    val version: AppVersion
    val networkConfig: NetworkConfig
}
