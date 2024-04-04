package io.monstarlab.localization

import android.annotation.SuppressLint
import android.content.Context
import androidx.startup.Initializer
import com.monstarlab.localization.BuildConfig
import dk.nodes.nstack.kotlin.NStack

class NStackInitializer : Initializer<NStack> {

    @SuppressLint("AppOpenMissing")
    override fun create(context: Context): NStack {
        NStack.translationClass = Translation::class.java
        NStack.init(context, BuildConfig.DEBUG)
        return NStack
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
