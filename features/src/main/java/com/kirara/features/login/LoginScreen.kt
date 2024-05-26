package com.kirara.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.kirara.core.util.Extensions.myToast
import com.kirara.features.login.section.LoginContent

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel =  hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val uiStateLogin by viewModel.uiStateLogin.collectAsState()

    Scaffold{ innerPadding ->
        Surface(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LoginContent(
                onButtonActionClicked = {
                    viewModel.loginApiCall(it)
                },
                navigateToRegister = navigateToRegister
            )
            HandleUiState(uiStateLogin = uiStateLogin, navigateToHome = navigateToHome, viewModel = viewModel)
        }
    }
}

@Composable
fun HandleUiState(
    uiStateLogin: UiState<BaseResponse<LoginData>>,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel,
){
    val context = LocalContext.current
    when (uiStateLogin) {
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
            val message = uiStateLogin.data.message ?: ""
            context.myToast(message)
            navigateToHome()
            viewModel.resetState()
        }

        is UiState.Error -> {
            context.myToast(uiStateLogin.errorMessage)
            viewModel.resetState()
        }
    }
}