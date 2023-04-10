package ru.macdroid.goblin.domain

import ru.smartro.installer.AppConstants

data class LoginState(
    val email: String = "v.zhdanov@saures.ru", //AppConstants.EMPTY_STRING
    val password: String = "123456", //AppConstants.EMPTY_STRING
    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    val isLoading: Boolean = false,
    val isLogin: Boolean = false,
    val authErrorMessage: String = AppConstants.EMPTY_STRING,
    val sid: String = AppConstants.EMPTY_STRING
)

sealed class LoginEvents {
    data class InputEmail(val email: String): LoginEvents()
    data class InputPassword(val password: String): LoginEvents()
    data class ButtonLoginClick(val email: String, val password: String): LoginEvents()
    data class ShowError(val errorText: String): LoginEvents()
    data class LoginSuccess(val token: String): LoginEvents()

    object ShowLoading: LoginEvents()
    object LongPressOnButton: LoginEvents()
}

sealed class LoginEffects {
    data class LoginToPostWithParams(val email: String, val password: String) : LoginEffects()
    data class SaveTokenToStore(val token: String) : LoginEffects()
}