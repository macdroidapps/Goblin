package ru.macdroid.goblin.data.remote.repository

import ru.macdroid.goblin.data.remote.api.LoginRetrofitClient
import ru.macdroid.goblin.data.remote.dto.toLogin
import ru.macdroid.goblin.domain.model.Login
import ru.macdroid.goblin.domain.repository.LoginRemoteRepository

import javax.inject.Inject

class LoginRemoteRepositoryImpl @Inject constructor(
    private val api: LoginRetrofitClient
) : LoginRemoteRepository {

    override suspend fun doLogin(login: String, password: String): Login {
        return api.doLogin(login, password).toLogin()
    }

}