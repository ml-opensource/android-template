package com.monstarlab.features.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.monstarlab.designsystem.components.AppTopBar
import com.monstarlab.designsystem.components.appbutton.AppButton
import com.monstarlab.designsystem.components.apptextfield.AppTextField
import com.monstarlab.designsystem.theme.AppTheme
import com.monstarlab.designsystem.theme.Theme

@Composable
fun LoginScreen(
    state: LoginState = LoginState(),
    actions: LoginActions = LoginActions()
) = Scaffold(
    topBar = { AppTopBar(title = "Login") }
) {
    Column(
        modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .padding(Theme.dimensions.big1),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppTextField(
            value = state.email,
            onValueChange = actions.onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = "E-Mail"
        )

        Spacer(modifier = Modifier.size(Theme.dimensions.medium3))

        AppTextField(
            value = state.password,
            onValueChange = actions.onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            error = state.error,
            visualTransformation = PasswordVisualTransformation(),
            placeholder = "Password"
        )
        Spacer(modifier = Modifier.size(Theme.dimensions.medium3))

        AppButton(
            text = "Login",
            onClick = actions.onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            isLoading = state.isLoading
        )
    }
}

@Composable
@Preview
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen()
    }
}
