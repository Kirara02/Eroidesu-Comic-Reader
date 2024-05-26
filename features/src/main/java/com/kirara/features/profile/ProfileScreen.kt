package com.kirara.features.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kirara.core.data.UiState
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.ui.template.MainTemplate
import com.kirara.core.util.Extensions.myToast
import com.kirara.features.navigation.GeneralScreen
import com.kirara.features.profile.section.ProfileContent
import com.kirara.features.register.RegisterViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToEditProfile: () -> Unit,
    navigateToChangePassword: () -> Unit,
    onUserLogout: () -> Unit,
) {
    val uiStateLogout by viewModel.uiStateLogout.collectAsState()
    MainTemplate {
        ProfileContent(navigateToEditProfile = navigateToEditProfile,navigateToChangePassword = navigateToChangePassword, viewModel = viewModel)
        HandleUiState(uiStateLogout = uiStateLogout, onUserLogout = onUserLogout, viewModel = viewModel)
    }
}


@Composable
fun HandleUiState(
    uiStateLogout: UiState<AuthResponse>,
    onUserLogout: () -> Unit,
    viewModel: ProfileViewModel,
){
    val context = LocalContext.current
    when (uiStateLogout) {
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
            val message = uiStateLogout.data.message ?: ""
            context.myToast(message)
            Log.d("Logout", "Navigating to login screen")
            onUserLogout()
            viewModel.resetState()
        }


        is UiState.Error -> {
            context.myToast(uiStateLogout.errorMessage)
            viewModel.resetState()
        }
    }
}