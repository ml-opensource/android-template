package com.monstarlab.designsystem.theme.dimensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Holder for the app dimensions that can be used to avoid hardcoded margins and paddings
 * Provides Default variant and [Small] that can be used for smaller devices
 */
class Dimensions(
    val medium1: Dp,
    val medium2: Dp,
    val medium3: Dp,
    val big1: Dp,
    val big2: Dp
) {
    companion object {
        val Small = Dimensions(
            medium1 = 12.dp,
            medium2 = 9.dp,
            medium3 = 6.dp,
            big1 = 18.dp,
            big2 = 16.dp,
        )

        val Default = Dimensions(
            medium1 = 16.dp,
            medium2 = 12.dp,
            medium3 = 8.dp,
            big1 = 24.dp,
            big2 = 32.dp,
        )
    }
}
