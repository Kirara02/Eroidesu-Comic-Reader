package com.kirara.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.request.RegisterRequest
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.domain.usecase.auth.LogoutUseCase
import com.kirara.core.network.error.handleAppError
import com.kirara.core.util.SharedPreferencesHelper
import com.kirara.core.util.UtilFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {
    private val _uiStateLogout: MutableStateFlow<UiState<AuthResponse>> = MutableStateFlow(
        UiState.Initial)
    val uiStateLogout: StateFlow<UiState<AuthResponse>> get() = _uiStateLogout

    private val token: String = "Bearer ${sharedPreferencesHelper.getAccessToken()}"


    fun logoutApiCall(){
        _uiStateLogout.value = UiState.Loading
        viewModelScope.launch {
            try {
                logoutUseCase.execute(token)
                    .catch {
                        _uiStateLogout.value = it.handleAppError()
                    }.collect{
                        _uiStateLogout.value = UiState.Success(it)
                        sharedPreferencesHelper.clearUserData()
                    }
            }catch (e: Exception){
                UtilFunctions.logE("ERROR :$e")
                _uiStateLogout.value = e.handleAppError()
            }
        }
    }

    fun resetState() {
        _uiStateLogout.value = UiState.Initial
    }
}