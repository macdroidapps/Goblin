package ru.macdroid.goblin.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.smartro.installer.data.local.datastore.repository.LoginDataStoreRepositoryImpl
import ru.macdroid.goblin.data.remote.repository.LoginRemoteRepositoryImpl
import ru.macdroid.goblin.data.remote.api.LoginRetrofitClient
import ru.macdroid.goblin.domain.repository.LoginDataStoreRepository
import ru.macdroid.goblin.domain.repository.LoginRemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideLoginClient(): LoginRetrofitClient {
        return Retrofit.Builder()
            .baseUrl("https://testapi.saures.ru/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(LoginRetrofitClient::class.java)
    }

    private val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build();

    private  var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideLoginRemoteRepository(api: LoginRetrofitClient): LoginRemoteRepository {
        return LoginRemoteRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLoginDataStoreRepository(
        @ApplicationContext context: Context,
    ): LoginDataStoreRepository {
        return LoginDataStoreRepositoryImpl(context)
    }

}
