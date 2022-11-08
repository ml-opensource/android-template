package com.monstarlab.designsystem.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.monstarlab.core.ui.extensions.bold
import com.monstarlab.designsystem.theme.AppTheme
import com.monstarlab.designsystem.theme.Theme

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackButtonClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Theme.colors.primary,
        contentColor = Theme.colors.onPrimary,
    ) {
        onBackButtonClick?.let { onBackClick ->
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
        }

        Text(
            text = title,
            style = Theme.typography.headline2.bold,
        )
    }
}

@Composable
@Preview(name = "App Top Bar - Default")
private fun PreviewAppTopBar() {
    AppTheme {
        AppTopBar(title = "Top Bar")
    }
}

@Composable
@Preview(name = "App Top Bar - Back Button Shown")
private fun PreviewAppTopBarWithBackButton() {
    AppTheme {
        AppTopBar(
            title = "Top Bar",
            onBackButtonClick = { }
        )
    }
}
