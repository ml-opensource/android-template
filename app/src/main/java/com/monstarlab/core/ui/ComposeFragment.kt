package com.monstarlab.core.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.monstarlab.core.ui.theme.AppTheme

abstract class ComposeFragment : Fragment() {

    abstract val content: @Composable () -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createComposableView(requireContext(), content)
    }
}

fun createComposableView(context: Context, content: @Composable () -> Unit): View {
    return ComposeView(context).apply {
        setContent {
            AppTheme {
                content.invoke()
            }
        }
    }
}
