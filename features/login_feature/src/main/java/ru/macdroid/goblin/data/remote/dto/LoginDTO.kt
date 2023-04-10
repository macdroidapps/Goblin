package ru.macdroid.goblin.data.remote.dto

import ru.macdroid.goblin.domain.model.Login

data class LoginDTO(
    val data: Data,
    val status: String,
    val errors: List<Errors>
)

data class Data(
    val api: Int,
    val role: Int,
    val sid: String,
    val telegram: Boolean
)

data class Errors(
    val name: String,
    val msg: String
)

// map LoginDTO to Login
fun LoginDTO.toLogin(): Login {
    return Login(
        status = status,
        sid = data.sid,
        errorName = errors.firstOrNull()?.name?.toIntOrNull(),
        errorMessage = errors.firstOrNull()?.msg
    )
}
