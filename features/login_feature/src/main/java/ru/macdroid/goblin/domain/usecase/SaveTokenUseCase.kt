package ru.macdroid.goblin.domain.usecase

import ru.macdroid.goblin.domain.repository.LoginDataStoreRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val loginDataStoreRepository: LoginDataStoreRepository
) {
    suspend operator fun invoke(token: String) {
        loginDataStoreRepository.saveToken(token)
    }
}