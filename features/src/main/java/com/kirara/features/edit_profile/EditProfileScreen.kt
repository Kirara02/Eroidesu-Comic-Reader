package com.kirara.features.edit_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kirara.core.data.UiState
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.LoginData
import com.kirara.core.data.model.response.User
import com.kirara.core.util.Extensions.myToast
import com.kirara.features.edit_profile.section.EditProfileContent
import com.kirara.features.login.LoginViewModel

@Composable
fun EditProfileScreen(
    navigateBack: () -> Unit,
    navigateOnSuccess: () -> Unit,
    viewModel: EditProfileViewModel = hiltViewModel()
){
    val uiStateUpdate by viewModel.uiStateUpdate.collectAsState()

    EditProfileContent(viewModel = viewModel, navigateBack = navigateBack)
    HandleUiState(uiStateUpdate = uiStateUpdate, navigateOnSuccess = navigateOnSuccess, viewModel = viewModel)
}


@Composable
fun HandleUiState(
    uiStateUpdate: UiState<BaseResponse<User>>,
    navigateOnSuccess: () -> Unit,
    viewModel: EditProfileViewModel,
){
    val context = LocalContext.current
    when (uiStateUpdate) {
        is UiState.Initial -> {
            /** Handle some function when prepared **/
        }

        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
        }

        is UiState.Success -> {
            val message = uiStateUpdate.data.message ?: ""
            context.myToast(message)
            navigateOnSuccess()
            viewModel.resetState()
        }

        is UiState.Error -> {
            context.myToast(uiStateUpdate.errorMessage)
            viewModel.resetState()
        }
    }
}