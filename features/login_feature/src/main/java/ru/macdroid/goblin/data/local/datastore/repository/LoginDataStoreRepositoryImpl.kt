package ru.smartro.installer.data.local.datastore.repository

import android.content.Context
import kotlinx.coroutines.runBlocking
import ru.smartro.installer.AppConstants
import ru.macdroid.macdroid.DataStoreManager
import ru.macdroid.goblin.domain.repository.LoginDataStoreRepository

class LoginDataStoreRepositoryImpl (
    private val context: Context
) : LoginDataStoreRepository {

    override suspend fun saveToken(token: String) {

        val dataStoreManager = DataStoreManager(context)

        runBlocking {
            dataStoreManager.saveStringToDataStore(
                key = AppConstants.TOKEN,
                value = token
            )
        }

        // Получаем значение из DataStore (для примера)
//        val name = dataStoreManager.getStringFromDataStore(AppConstants.TOKEN, "").first()
    }
}