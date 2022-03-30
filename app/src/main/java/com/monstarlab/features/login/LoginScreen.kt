package com.monstarlab.features.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monstarlab.core.ui.components.AppButton

@Composable
fun LoginScreen(state: LoginViewState, onEvent: (LoginScreenEvent) -> Unit) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.email,
                enabled = !state.isLoading,
                onValueChange = { onEvent(LoginScreenEvent.LoginChange(it) ) }
            )

            Spacer(modifier = Modifier.size(4.dp))

            TextField(
                value = state.password,
                enabled = !state.isLoading,
                onValueChange = { onEvent(LoginScreenEvent.PasswordChange(it) ) },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.size(4.dp))
            AppButton(text = "Login") {
              onEvent.invoke(LoginScreenEvent.LoginButtonPress)
            }
            Spacer(modifier = Modifier.size(4.dp))

            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(state = LoginViewState(), {})
}
