package com.monstarlab.core.sharedui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    defaultFontFamily = appFontFamily,

    h1 = TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),

    h2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),

    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),

    h5 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    h6 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),

    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),

    subtitle2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),

    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    button = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
)
