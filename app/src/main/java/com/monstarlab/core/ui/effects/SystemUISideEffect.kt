package com.monstarlab.core.ui.effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUISideEffect(block: (SystemUiController) -> Unit) {
    val controller = rememberSystemUiController()
    SideEffect { block.invoke(controller) }
}
