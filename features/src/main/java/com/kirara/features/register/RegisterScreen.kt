package com.kirara.features.register

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
import com.kirara.core.data.model.response.AuthResponse
import com.kirara.core.util.Extensions.myToast
import com.kirara.features.register.section.RegisterContent

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel =  hiltViewModel(),
    navigateBack: () -> Unit,
){
    val uiStateRegister by viewModel.uiStateRegister.collectAsState()

    Scaffold{ innerPadding ->
        Surface(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            RegisterContent(
                onButtonActionClicked = {
                    viewModel.registerApiCall(it)
                },
                navigateBack = navigateBack
            )
            HandleUiState(uiStateRegister = uiStateRegister, navigateBack = navigateBack, viewModel = viewModel)
        }
    }
}


@Composable
fun HandleUiState(
    uiStateRegister: UiState<AuthResponse>,
    navigateBack: () -> Unit,
    viewModel: RegisterViewModel,
){
    val context = LocalContext.current
    when (uiStateRegister) {
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
            val message = uiStateRegister.data.message ?: ""
            context.myToast(message)
            navigateBack()
            viewModel.resetState()
        }

        is UiState.Error -> {
            context.myToast(uiStateRegister.errorMessage)
            viewModel.resetState()
        }
    }
}