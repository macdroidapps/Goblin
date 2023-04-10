package ru.smartro.installer.domain.reduce

import ru.smartro.installer.AppConstants
import ru.macdroid.goblin.domain.LoginEffects
import ru.macdroid.goblin.domain.LoginEvents
import ru.macdroid.goblin.domain.LoginState
import ru.smartro.installer.isValidEmail
import ru.smartro.installer.isValidPassword

fun LoginState.reduce(event: LoginEvents): Pair<LoginState, Set<LoginEffects>> = when (event) {

    is LoginEvents.InputEmail -> copy(
        email = event.email,
        emailError = !event.email.isValidEmail(),
        isLogin = false,
        authErrorMessage = AppConstants.EMPTY_STRING
    ) to emptySet()


    is LoginEvents.InputPassword -> copy(
        password = event.password,
        passwordError = !event.password.isValidPassword(),
        isLogin = false,
        authErrorMessage = AppConstants.EMPTY_STRING
    ) to emptySet()

    is LoginEvents.ButtonLoginClick -> copy(
        email = event.email,
        password = event.password,
        authErrorMessage = AppConstants.EMPTY_STRING,
        isLogin = false
    ) to setOf(LoginEffects.LoginToPostWithParams(event.email, event.password))

    is LoginEvents.ShowLoading -> copy(
        isLoading = true,
        isLogin = false
    ) to emptySet()

    is LoginEvents.LoginSuccess -> copy(
        isLoading = false,
        isLogin = true,
        sid = event.token,
        authErrorMessage = AppConstants.EMPTY_STRING
    ) to setOf(LoginEffects.SaveTokenToStore(event.token))

    is LoginEvents.ShowError -> copy(
        isLoading = false,
        isLogin = false,
        authErrorMessage = event.errorText
    ) to emptySet()

    LoginEvents.LongPressOnButton -> copy(
        isLoading = true,
        isLogin = false
    ) to emptySet()
}