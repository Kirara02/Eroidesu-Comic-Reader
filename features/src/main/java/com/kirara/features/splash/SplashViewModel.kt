package com.kirara.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.request.RefreshTokenRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.LoginData
import com.kirara.core.data.model.response.User
import com.kirara.core.domain.usecase.auth.RefreshTokenUseCase
import com.kirara.core.domain.usecase.user.GetUserUseCase
import com.kirara.core.network.error.handleAppError
import com.kirara.core.util.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> get() = _isUserLoggedIn

    private val _uiStateUser: MutableStateFlow<UiState<BaseResponse<User>>> = MutableStateFlow(UiState.Initial)
    val uiStateUser: StateFlow<UiState<BaseResponse<User>>> get() = _uiStateUser

    private val _uiStateTokenRefresh: MutableStateFlow<UiState<BaseResponse<LoginData>>> = MutableStateFlow(UiState.Initial)
    val uiStateTokenRefresh: StateFlow<UiState<BaseResponse<LoginData>>> get() = _uiStateTokenRefresh

    init {
        checkTokenAndLogin()
    }

    private fun checkTokenAndLogin() {
        viewModelScope.launch {
            val token = "Bearer ${sharedPreferencesHelper.getAccessToken()}"
            if (token.isNullOrEmpty()) {
                _isUserLoggedIn.value = false
            } else {
                try {
                    _uiStateUser.value = UiState.Loading
                    getUserUseCase.execute(token)
                        .catch { e ->
                            if (e is HttpException && e.code() == 401) {
                                // Token expired, try refreshing
                                refreshAccessToken()
                            } else {
                                _isUserLoggedIn.value = false
                                _uiStateUser.value = e.handleAppError()
                            }
                        }
                        .collect { response ->
                            _uiStateUser.value = UiState.Success(response)
                            _isUserLoggedIn.value = true
                        }
                }catch (e: Exception){
                    _isUserLoggedIn.value = false
                    _uiStateUser.value = e.handleAppError()
                }
            }
        }
    }

    private suspend fun refreshAccessToken() {
        val refreshToken = "Bearer ${sharedPreferencesHelper.getRefreshToken()}"
        if (refreshToken.isNullOrEmpty()) {
            _isUserLoggedIn.value = false
            return
        }

        try {
            _uiStateTokenRefresh.value = UiState.Loading
            refreshTokenUseCase.execute(RefreshTokenRequest(refreshToken))
                .catch { e ->
                    _isUserLoggedIn.value = false
                    _uiStateTokenRefresh.value = e.handleAppError()
                }
                .collect { response ->
                    sharedPreferencesHelper.saveAccessToken(response.data.accessToken)
                    sharedPreferencesHelper.saveRefreshToken(response.data.refreshToken)
                    _uiStateTokenRefresh.value = UiState.Success(response)
                    checkTokenAndLogin()
                }
        }catch (e: Exception){
            _isUserLoggedIn.value = false
            _uiStateUser.value = e.handleAppError()
        }
    }
}

