package com.kirara.core.network.error
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.kirara.core.data.UiState
import retrofit2.HttpException
import java.io.IOException

sealed class AppError {
    data class NetworkError(val message: String) : AppError()
    data class UnauthorizedError(val message: String) : AppError()
    data class NotFoundError(val message: String) : AppError()
    data class ForbiddenError(val message: String) : AppError()
    data class ServerError(val message: String) : AppError()
    data class ValidationError(val errors: Map<String, List<String>>) : AppError()
    data class CustomError(val message: String) : AppError()
    data class UnknownError(val message: String) : AppError()
}

private fun AppError.toUiStateError(): UiState.Error {
    return when (this) {
        is AppError.NetworkError -> UiState.Error(message)
        is AppError.UnauthorizedError -> UiState.Error(message)
        is AppError.NotFoundError -> UiState.Error(message)
        is AppError.ForbiddenError -> UiState.Error(message)
        is AppError.ServerError -> UiState.Error(message)
        is AppError.ValidationError -> {
            // Ambil hanya pesan kesalahan pertama
            val firstErrorMessage = errors.entries.firstOrNull()?.let {
                "${it.key}: ${it.value.firstOrNull()}"
            } ?: "Unknown validation error"
            UiState.Error(firstErrorMessage)
        }
        is AppError.CustomError -> UiState.Error(message)
        is AppError.UnknownError -> UiState.Error(message)
    }
}

fun Throwable.handleAppError(): UiState.Error {
    val appError = when (this) {
        is IOException -> AppError.NetworkError("Oops, No internet connection.")
        is HttpException -> {
            val errorBody = this.response()?.errorBody()?.string()
            val errorMessage = this.message().toString()
            when (this.code()) {
                400 -> {
                    // Parsing the error body as validation error
                    val type = object : TypeToken<Map<String, List<String>>>() {}.type
                    val validationErrors: Map<String, List<String>>? = try {
                        Gson().fromJson<Map<String, List<String>>>(errorBody, type)
                    } catch (e: JsonSyntaxException) {
                        null
                    }
                    if (validationErrors != null) {
                        AppError.ValidationError(validationErrors)
                    } else {
                        AppError.CustomError(errorBody ?: errorMessage)
                    }
                }
                401 -> {
                    val customError: String? = try {
                        Gson().fromJson(errorBody, Map::class.java)["message"] as? String
                    } catch (e: JsonSyntaxException) {
                        null
                    }
                    AppError.UnauthorizedError(customError ?: errorMessage)
                }
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

