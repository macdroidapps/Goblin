package ru.macdroid.goblin.domain.reduce

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import ru.smartro.installer.IEffHandler
import ru.smartro.installer.State
import ru.macdroid.goblin.domain.LoginEffects
import ru.macdroid.goblin.domain.LoginEvents
import ru.macdroid.goblin.domain.usecase.LoginUseCase
import ru.macdroid.goblin.domain.usecase.SaveTokenUseCase
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class LoginEFFHandler @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : IEffHandler<LoginEffects, LoginEvents> {

    private lateinit var localJob: Job
    override suspend fun handle(effect: LoginEffects, commit: (LoginEvents) -> Unit) {
        when (effect) {

            is LoginEffects.LoginToPostWithParams -> {
                localJob = Job()

                withContext(context = coroutineContext + localJob) {
                    val email = effect.email
                    val password = effect.password

                    loginUseCase(
                        login = email,
                        password = password
                    ).onEach { result ->

                        when (result) {
                            is State.Loading -> commit(
                                LoginEvents.ShowLoading
                            )
                            is State.Success -> commit(
                                LoginEvents.LoginSuccess(
                                    result.data?.sid ?: ""
                                )
                            )
                            is State.Error -> commit(
                                LoginEvents.ShowError(
                                    result.message ?: "Error"
                                )
                            )
                        }

                    }.launchIn(this)
                }
            }

            is LoginEffects.SaveTokenToStore -> {

                localJob = Job()

                withContext(context = coroutineContext + localJob) {
                    saveTokenUseCase(effect.token)
                }
            }
        }
    }
}