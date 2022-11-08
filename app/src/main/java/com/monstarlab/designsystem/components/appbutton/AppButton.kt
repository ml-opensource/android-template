package com.monstarlab.designsystem.components.appbutton

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monstarlab.designsystem.theme.AppTheme
import com.monstarlab.designsystem.theme.Theme

@Composable
fun AppButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    type: AppButtonType = AppButtonType.Filled,
    onClick: () -> Unit
) {
    when (type) {
        AppButtonType.Filled -> PrimaryButton(
            modifier = modifier,
            onClick = onClick,
            enabled = enabled && !isLoading,
            content = { ButtonContent(text = text, isLoading = isLoading) }
        )

        AppButtonType.Outlined -> OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled && !isLoading,
            content = { ButtonContent(text = text, isLoading = isLoading) },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Theme.colors.secondary,
                backgroundColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, Theme.colors.secondary)
        )

        AppButtonType.Text -> {
            TextButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled && !isLoading,
                content = {
                    ButtonContent(text = text, isLoading = isLoading)
                },
            )
        }
    }
}

@Composable
private fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    val colors = ButtonDefaults.buttonColors()
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        content = content,
        colors = colors,
        contentPadding = PaddingValues(vertical = Theme.dimensions.medium2)
    )
}

@Composable
private fun RowScope.ButtonContent(text: String, isLoading: Boolean) {
    Crossfade(targetState = isLoading) {
        if (it) {
            CircularProgressIndicator(
                color = Theme.colors.primary,
                modifier = Modifier.size(24.dp),
                strokeWidth = 1.dp
            )
        } else {
            Text(
                text = text,
                style = Theme.typography.button
            )
        }
    }
}

@Composable
@Preview(name = "Primary Button")
private fun PreviewButtonPrimary() {
    AppTheme {
        AppButton(
            text = "Primary",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview(name = "Primary Button - Loading")
private fun PreviewButtonPrimaryWithLoading() {
    AppTheme {
        AppButton(
            text = "Primary",
            onClick = {},
            isLoading = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
