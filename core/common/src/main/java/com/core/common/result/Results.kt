package com.core.common.result

sealed interface Results<out T> {
    object Loading : Results<Nothing>
    data class Success<T>(val data: T? = null) : Results<T>
    data class Error(
        val errorMessage: String? = null,
        val errorCode: Int? = null,
    ) : Results<Nothing>
}