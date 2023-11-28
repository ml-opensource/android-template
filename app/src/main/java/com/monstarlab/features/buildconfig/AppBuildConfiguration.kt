package com.monstarlab.features.buildconfig

import com.monstarlab.BuildConfig
import com.monstarlab.core.config.AppFlavour
import com.monstarlab.core.config.AppVersion
import com.monstarlab.core.config.BuildConfiguration
import com.monstarlab.core.config.NetworkConfig
import javax.inject.Inject

class AppBuildConfiguration @Inject constructor() : BuildConfiguration {
    override val flavour: AppFlavour
        get() {
            return when (BuildConfig.FLAVOR) {
                "development" -> AppFlavour.Development
                "staging" -> AppFlavour.Staging
                "production" -> AppFlavour.Production
                else -> throw IllegalStateException(
                    "Flavour ${BuildConfig.FLAVOR} is not supported",
                )
            }
        }
    override val version: AppVersion = AppVersion(
        versionName = BuildConfig.VERSION_NAME,
        buildNumber = BuildConfig.VERSION_CODE,
    )
    override val networkConfig: NetworkConfig = NetworkConfig(BuildConfig.API_URL)
}
