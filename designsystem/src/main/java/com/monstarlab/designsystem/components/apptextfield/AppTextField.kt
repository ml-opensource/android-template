package com.monstarlab.designsystem.components.apptextfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.monstarlab.designsystem.theme.AppTheme
import com.monstarlab.designsystem.theme.Theme

@Composable
fun AppTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    label: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            isError = error != null
        )

        AnimatedVisibility(visible = error != null) {
            Text(
                text = error ?: "",
                color = Theme.colors.error
            )
        }
    }
}

@Composable
@Preview
private fun PreviewTextField() {
    var value by remember { mutableStateOf("value") }
    AppTheme {
        Surface(color = Theme.colors.background) {
            AppTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Placeholder",
                label = "Label",
                error = if (value == "error") "This is error" else null
            )
        }
    }
}
