package ru.smartro.installer

data class ErrorBody(
    val success: Boolean? = null,
    val message: String? = null,
    val status: String? = null
)