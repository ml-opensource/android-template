package com.monstarlab.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.monstarlab.designsystem.theme.dimensions.Dimensions
import com.monstarlab.designsystem.theme.dimensions.LocalDimensions
import com.monstarlab.designsystem.theme.dimensions.ProvideDimensions
import com.monstarlab.designsystem.theme.typography.AppTypography
import com.monstarlab.designsystem.theme.typography.LocalAppTypography
import com.monstarlab.designsystem.theme.typography.ProvideAppTypography
import com.monstarlab.designsystem.theme.typography.Typography

/**
 * Shortcut to obtain App Theme values instead of using [MaterialTheme]
 * - Provides custom AppTypography instead of Material one
 * - Provides access to dimensions, that can vary based on device size
 */
object Theme {
    val typography: AppTypography @Composable get() = LocalAppTypography.current
    val colors: Colors @Composable get() = MaterialTheme.colors
    val shapes: Shapes @Composable get() = MaterialTheme.shapes
    val dimensions: Dimensions @Composable get() = LocalDimensions.current
}

/**
 * Main theme provider
 */
@Composable
fun AppTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    isSmallDevice: Boolean = isSmallDevice(),
    content: @Composable () -> Unit
) {
    val dimensions = if (isSmallDevice) Dimensions.Small else Dimensions.Default
    ProvideDimensions(dimensions = dimensions) {
        ProvideAppTypography {
            MaterialTheme(
                typography = Typography,
                shapes = Shapes,
                content = content,
                colors = if (isDarkMode) DarkColors else LightColors
            )
        }
    }
}

private val LightColors = lightColors(
    primary = MonstarlabYellow,
    onPrimary = Black,
    background = LightGrey,
    onBackground = DarkGrey,
    onSurface = Black,
    surface = White,
    error = Red
)

private val DarkColors = darkColors(
    primary = MonstarlabYellow,
    onPrimary = Black,
    background = DarkGrey,
    onBackground = White,
    onSurface = White,
    surface = Black,
    error = Red
)

@Composable
private fun isSmallDevice(): Boolean = LocalConfiguration.current.screenWidthDp <= 360
