package com.kirara.features.change_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.request.ChangePasswordRequest
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.DefaultResponse
import com.kirara.core.domain.usecase.user.ChangePasswordUseCase
import com.kirara.core.network.error.handleAppError
import com.kirara.core.util.UtilFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<DefaultResponse>> = MutableStateFlow(
        UiState.Initial)
    val uiState: StateFlow<UiState<DefaultResponse>> get() = _uiState


    fun changePasswordApiCall(request: ChangePasswordRequest){
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                changePasswordUseCase.executeWithToken(request)
                    .catch {
                        _uiState.value = it.handleAppError()
                    }.collect{
                        _uiState.value = UiState.Success(it)
                    }
            }catch (e: Exception){
                UtilFunctions.logE("ERROR :$e")
                _uiState.value = e.handleAppError()
            }
        }
    }
}