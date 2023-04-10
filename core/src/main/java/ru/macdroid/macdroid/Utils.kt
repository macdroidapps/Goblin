package ru.smartro.installer

import com.google.gson.GsonBuilder


class Utils {
    companion object {

        inline fun <reified T> getObjectFromJson(value: String): T? {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return gson.fromJson(value, T::class.java)
        }

    }
}
