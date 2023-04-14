package com.monstarlab.core.ui.extensions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun LazyListScope.animatedVisibilityItem(
    key: Any? = null,
    visible: Boolean,
    enter: EnterTransition = fadeIn() + expandIn(),
    exit: ExitTransition = fadeOut() + shrinkOut(),
    content: @Composable () -> Unit
) {
    item(
        key = key,
        content = {
            AnimatedVisibility(
                visible = visible,
                enter = enter,
                exit = exit,
                content = { content.invoke() }
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.animatedVisibilityHeader(
    key: Any? = null,
    visible: Boolean,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn() + expandIn(),
    exit: ExitTransition = fadeOut() + shrinkOut(),
    content: @Composable () -> Unit
) {
    stickyHeader(
        key = key,
        content = {
            AnimatedVisibility(
                modifier = modifier,
                visible = visible,
                enter = enter,
                exit = exit,
                content = { content.invoke() }
            )
        }
    )
}
