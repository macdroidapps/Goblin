package ru.macdroid.goblin.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.macdroid.goblin.domain.LoginEvents

@Composable
fun LoginRoot(
    onNavigateNext: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.feature.state.collectAsState()

    LaunchedEffect(key1 = state, block = {
        if (state.isLogin) {
            onNavigateNext()
        }
    })

    when (state.isLoading) {
        true -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        false -> {
            LoginScreen(
                state = state,
                onEmailInput = {
                    viewModel.onEvent(LoginEvents.InputEmail(it))
                },
                onPasswordInput = {
                    viewModel.onEvent(LoginEvents.InputPassword(it))
                },
                onClick = {
                    viewModel.onEvent(LoginEvents.ButtonLoginClick(state.email, state.password))
                }
            )
        }
    }
}