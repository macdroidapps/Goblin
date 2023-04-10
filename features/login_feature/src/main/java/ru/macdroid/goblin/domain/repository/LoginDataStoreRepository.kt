package ru.macdroid.goblin.domain.repository

interface LoginDataStoreRepository {
    suspend fun saveToken(token: String)
}