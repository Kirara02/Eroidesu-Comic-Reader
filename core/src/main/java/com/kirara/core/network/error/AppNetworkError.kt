package com.kirara.core.network.error

import com.kirara.core.data.UiState
import retrofit2.HttpException
import java.io.IOException


sealed class AppError {
    data class NetworkError(val message: String) : AppError()
    data class UnauthorizedError(val message: String) : AppError()
    data class NotFoundError(val message: String) : AppError()
    data class ForbiddenError(val message: String) : AppError()
    data class ServerError(val message: String) : AppError()
    data class UnknownError(val message: String) : AppError()
}

private fun AppError.toUiStateError(): UiState.Error {
    return when (this) {
        is AppError.NetworkError -> UiState.Error(message)
        is AppError.UnauthorizedError -> UiState.Error(message)
        is AppError.NotFoundError -> UiState.Error(message)
        is AppError.ForbiddenError -> UiState.Error(message)
        is AppError.ServerError -> UiState.Error(message)
        is AppError.UnknownError -> UiState.Error(message)
    }
}

fun Throwable.handleAppError(): UiState.Error {
    val appError = when (this) {
        is IOException -> AppError.NetworkError("Oops, No internet connection.")
        is HttpException -> {
            val errorMessage = this.message().toString()
            when (this.code()) {
                400 -> AppError.UnknownError(errorMessage)
                401 -> AppError.UnauthorizedError(errorMessage)
                403 -> AppError.ForbiddenError(errorMessage)
                404 -> AppError.NotFoundError(errorMessage)
                500 -> AppError.ServerError("Internal Server Error")
                503 -> AppError.ServerError("Service Unavailable")
                else -> AppError.UnknownError("Something went wrong: $errorMessage")
            }
        }
        else -> AppError.UnknownError(this.message.toString())
    }
    return appError.toUiStateError()
}
