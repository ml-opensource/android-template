package com.monstarlab.designsystem.theme.typography

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    defaultFontFamily = FontFamily,
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),

    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 32.sp
    ),

    h3 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    h5 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

    h6 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),

    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 21.sp
    ),

    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    button = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    )
)
