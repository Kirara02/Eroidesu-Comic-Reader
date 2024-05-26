package com.kirara.core.data

sealed class UiState<out T : Any> {
    object Initial : UiState<Nothing>()

    object Loading : UiState<Nothing>()

    data class Success<out T : Any>(val data: T) : UiState<T>()

    data class Error(val errorMessage : String) : UiState<Nothing>()
}
