package com.kirara.features.change_password

import android.util.Log
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
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.data.model.response.DefaultResponse
import com.kirara.core.ui.template.MainTemplate
import com.kirara.core.util.Extensions.myToast
import com.kirara.features.change_password.section.ChangePasswordContent
import com.kirara.features.profile.ProfileViewModel
import com.kirara.features.profile.section.ProfileContent

@Composable
fun ChangePasswordScreen(
    navigateBack: () -> Unit,
    navigateOnSuccess: () -> Unit,
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ChangePasswordContent(viewModel = viewModel, navigateBack = navigateBack)
    HandleUiState(uiState = uiState, navigateOnSuccess = navigateOnSuccess, viewModel = viewModel)
}

@Composable
fun HandleUiState(
    uiState: UiState<DefaultResponse>,
    navigateOnSuccess: () -> Unit,
    viewModel: ChangePasswordViewModel,
){
    val context = LocalContext.current
    when (uiState) {
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
            val message = uiState.data.message ?: ""
            context.myToast(message)
            Log.d("Logout", "Navigating to login screen")
            navigateOnSuccess()
            viewModel.resetState()
        }


        is UiState.Error -> {
            context.myToast(uiState.errorMessage)
            viewModel.resetState()
        }
    }
}