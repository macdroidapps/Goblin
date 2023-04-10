package ru.macdroid.goblin.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.macdroid.goblin.presentation.components.LoginForm
import ru.macdroid.goblin.domain.LoginState

@Composable
fun LoginScreen(
    state: LoginState,
    onClick: () -> Unit,
    onEmailInput: (email: String) -> Unit,
    onPasswordInput: (password: String) -> Unit,
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = state, block = {
        if (state.authErrorMessage.isNotBlank()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = state.authErrorMessage
                )
            }
        }
    })

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    elevation = 8.dp,
                    backgroundColor = Color.Gray,
                    contentColor = Color.White,
                    content = {
                        Text(data.message)
                    }
                )
            }
        },
        content = {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LoginForm(
                    state = state,
                    onClick = onClick,
                    onEmailInput = onEmailInput,
                    onPasswordInput = onPasswordInput
                )

            }
        }
    )
}