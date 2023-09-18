package com.monstarlab.designsystem.theme.typography

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle

/**
 * Custom holder for the Text styles instead of the one used in Material Design
 * Here You can put the styles the app needs and the one specified in you projects Figma
 *
 * secondary constructor uses Material Theme typography  from [Typography]
 * to maintain some corelation with Material Specification
 */
data class AppTypography(
    val headline1: TextStyle,
    val headline2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button: TextStyle
) {

    constructor(typography: Typography) : this(
        headline1 = typography.h1,
        headline2 = typography.h2,
        body1 = typography.body1,
        body2 = typography.body2,
        button = typography.button
    )
}
