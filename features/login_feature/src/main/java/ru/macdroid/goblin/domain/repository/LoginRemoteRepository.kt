package ru.macdroid.goblin.domain.repository

import ru.macdroid.goblin.domain.model.Login

interface LoginRemoteRepository {
    suspend fun doLogin(login: String, password: String): Login
}