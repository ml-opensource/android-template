package com.monstarlab.core.ui.extensions

import androidx.compose.ui.graphics.Color

fun Color.Companion.fromHex(hex: String): Color {
    return Color(android.graphics.Color.parseColor(hex))
}

val Color.alpha20 get() = copy(alpha = 0.2f)
