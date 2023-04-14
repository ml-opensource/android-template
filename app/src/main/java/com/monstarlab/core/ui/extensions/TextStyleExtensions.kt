package com.monstarlab.core.ui.extensions

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

val TextStyle.bold: TextStyle get() = copy(fontWeight = FontWeight.Bold)

val TextStyle.normal: TextStyle get() = copy(fontWeight = FontWeight.Normal)

val TextStyle.light: TextStyle get() = copy(fontWeight = FontWeight.Light)
