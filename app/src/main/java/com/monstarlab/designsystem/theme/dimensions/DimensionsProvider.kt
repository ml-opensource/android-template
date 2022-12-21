package com.monstarlab.designsystem.theme.dimensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDimensions = staticCompositionLocalOf {
    Dimensions.Default
}

@Composable
fun ProvideDimensions(
    dimensions: Dimensions,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalDimensions provides dimensions) {
        content.invoke()
    }
}
