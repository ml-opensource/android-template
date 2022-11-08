package com.monstarlab.designsystem.theme.typography

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(Typography)
}

@Composable
fun ProvideAppTypography(
    typography: AppTypography = AppTypography(Typography),
    content: @Composable () -> Unit = {}
) {
    CompositionLocalProvider(LocalAppTypography provides typography) {
        content.invoke()
    }
}
