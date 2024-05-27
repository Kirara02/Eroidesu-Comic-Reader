package com.kirara.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.request.LoginRequest
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.LoginData
import com.kirara.core.domain.usecase.auth.LoginUseCase
import com.kirara.core.network.error.handleAppError
import com.kirara.core.util.SharedPreferencesHelper
import com.kirara.core.util.UtilFunctions.logE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {
    private val _uiStateLogin: MutableStateFlow<UiState<BaseResponse<LoginData>>> = MutableStateFlow(UiState.Initial)
    val uiStateLogin: StateFlow<UiState<BaseResponse<LoginData>>> get() = _uiStateLogin

    fun loginApiCall(request: LoginRequest){
        _uiStateLogin.value = UiState.Loading
        viewModelScope.launch {
            try {
                loginUseCase.execute(request)
                    .catch {
                        _uiStateLogin.value = it.handleAppError()
                        logE("ERROR :$it")
                    }.collect{
                        _uiStateLogin.value = UiState.Success(it)
                        it.data.let { user ->
                            sharedPreferencesHelper.saveAccessToken(user.accessToken)
                            sharedPreferencesHelper.saveRefreshToken(user.refreshToken)
                            sharedPreferencesHelper.saveName(user.name)
                            sharedPreferencesHelper.saveEmail(user.email)
                            user.profilePicture?.let { profileUrl ->
                                sharedPreferencesHelper.saveProfileUrl(profileUrl)
                            }
                        }
                    }
            } catch (e: Exception){
                logE("ERROR :$e")
                _uiStateLogin.value = e.handleAppError()
            }
        }
    }

    fun resetState() {
        _uiStateLogin.value = UiState.Initial
    }
}