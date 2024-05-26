package com.kirara.features.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirara.core.data.UiState
import com.kirara.core.data.model.request.ChangePasswordRequest
import com.kirara.core.data.model.request.UpdateUserRequest
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.User
import com.kirara.core.domain.usecase.user.UpdateUserUseCase
import com.kirara.core.network.error.handleAppError
import com.kirara.core.util.SharedPreferencesHelper
import com.kirara.core.util.UtilFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {
    private val _uiStateUpdate: MutableStateFlow<UiState<BaseResponse<User>>> = MutableStateFlow(
        UiState.Initial)
    val uiStateUpdate: StateFlow<UiState<BaseResponse<User>>> get() = _uiStateUpdate

    private val _name = MutableStateFlow<String?>(null)
    val name: StateFlow<String?> get() = _name

    private val _email = MutableStateFlow<String?>(null)
    val email: StateFlow<String?> get() = _email


    init {
        loadDataFromPreferences()
    }

    private fun loadDataFromPreferences() {
        viewModelScope.launch {
            _name.value = sharedPreferencesHelper.getName()
            _email.value = sharedPreferencesHelper.getEmail()
        }
    }

    fun updateUserApiCall(request: UpdateUserRequest){
        _uiStateUpdate.value = UiState.Loading
        viewModelScope.launch {
            try {
                updateUserUseCase.executeWithToken(request)
                    .catch {
                        _uiStateUpdate.value = it.handleAppError()
                        UtilFunctions.logE("ERROR :$it")
                    }.collect{
                        _uiStateUpdate.value = UiState.Success(it)
                        it.data.let { user ->
                            sharedPreferencesHelper.saveName(user.name)
                            sharedPreferencesHelper.saveEmail(user.email)
                        }
                    }
            } catch (e: Exception){
                UtilFunctions.logE("ERROR :$e")
                _uiStateUpdate.value = e.handleAppError()
            }
        }
    }

}