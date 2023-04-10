package ru.macdroid.goblin.domain.model

data class Login(
    val status: String,
    val sid: String? = null,
    val errorName: Int? = null,
    val errorMessage: String? = null,
)