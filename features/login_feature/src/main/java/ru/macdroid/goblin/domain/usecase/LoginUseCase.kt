package ru.macdroid.goblin.domain.usecase

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.macdroid.goblin.domain.model.Login
import ru.smartro.installer.State
import ru.macdroid.goblin.domain.repository.LoginRemoteRepository
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRemoteRepository
) {
    operator fun invoke(login: String, password: String): Flow<State<Login>> = flow {
        try {

            emit(State.Loading())

            val response = loginRepository.doLogin(login = login, password = password)

            when (response.status) {
                "ok" -> {
                    emit(State.Success(response))
                }
                "bad" -> {
                    emit(State.Error(response.errorMessage ?: "Unknown error"))
                }
            }

        } catch (e: HttpException) {

            emit(State.Error("${e.code()} ${e.localizedMessage}"))

        } catch (e: IOException) {
            emit(State.Error("e2: $e"))
        } catch (e: Exception) {
            emit(State.Error("e3: $e"))
        }
    }
}