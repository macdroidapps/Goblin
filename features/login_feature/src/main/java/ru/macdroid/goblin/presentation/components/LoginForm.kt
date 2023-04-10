package ru.macdroid.goblin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ru.macdroid.goblin.login.R

import ru.smartro.installer.AppConstants
import ru.macdroid.goblin.domain.LoginState

@Composable
fun LoginForm(
    state: LoginState,
    onClick: () -> Unit,
    onEmailInput: (email: String) -> Unit,
    onPasswordInput: (password: String) -> Unit,
) {

    val showPassword = remember { mutableStateOf(false) }

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "SmartRO",
        modifier = Modifier
            .width(100.dp)
            .height(32.dp)
    )

    Spacer(modifier = Modifier.height(50.dp))

    OutlinedTextField(
        value = state.email,
        onValueChange = {
            onEmailInput(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .testTag(AppConstants.LOGIN_EMAIL_TF),
        label = { Text(text = stringResource(R.string.login_email_tf)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        singleLine = true,
        isError = state.emailError
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = state.password,
        onValueChange = {
            onPasswordInput(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .testTag(AppConstants.LOGIN_PASSWORD_TF),
        label = {
            Text(
                text = stringResource(
                    R.string.login_password_tf
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = null
            )
        },
        isError = state.passwordError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = { showPassword.value = !showPassword.value }
            ) {
                Image(
                    painter = painterResource(id = if (showPassword.value) R.drawable.ic_visibility_off else R.drawable.ic_visibility),
                    contentDescription = if (showPassword.value) "Hide password" else "Show password",
                )
            }
        },
        visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation()
    )

    Spacer(modifier = Modifier.height(50.dp))

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp)
            .testTag(AppConstants.LOGIN_BUTTON),
        enabled = !state.emailError && !state.passwordError,
    ) {
        Text(
            text = stringResource(R.string.login_button_text)
        )
    }
}
