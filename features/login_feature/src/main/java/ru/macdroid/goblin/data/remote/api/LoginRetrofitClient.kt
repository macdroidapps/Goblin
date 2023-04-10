package ru.macdroid.goblin.data.remote.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.macdroid.goblin.data.remote.dto.LoginDTO
import ru.macdroid.goblin.domain.model.Login

interface LoginRetrofitClient {
    @FormUrlEncoded
    @POST("/login")
    suspend fun doLogin(
        @Field("email") login: String,
        @Field("password") password: String
    ): LoginDTO
}