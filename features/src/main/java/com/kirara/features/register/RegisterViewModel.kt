package com.kirara.features.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.request.RegisterRequest
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.domain.usecase.auth.RegisterUseCase
import com.kirara.core.network.error.handleAppError
import com.kirara.core.util.UtilFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _uiStateRegister: MutableStateFlow<UiState<AuthResponse>> = MutableStateFlow(UiState.Initial)
    val uiStateRegister: StateFlow<UiState<AuthResponse>> get() = _uiStateRegister

    fun registerApiCall(request: RegisterRequest) {
        _uiStateRegister.value = UiState.Loading
        viewModelScope.launch {
            try {
                registerUseCase.execute(request)
                    .catch {
                        it.printStackTrace()
                        _uiStateRegister.value = it.handleAppError()
                        UtilFunctions.logE("ERROR :$it")
                    }
                    .collect {
                        _uiStateRegister.value = UiState.Success(it)
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                UtilFunctions.logE("ERROR :$e")
                _uiStateRegister.value = e.handleAppError()
            }
        }
    }

    fun resetState() {
        _uiStateRegister.value = UiState.Initial
    }
}
