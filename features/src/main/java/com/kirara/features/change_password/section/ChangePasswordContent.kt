package com.kirara.features.change_password.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.kirara.core.R
import com.kirara.core.data.model.request.ChangePasswordRequest
import com.kirara.core.data.model.request.UpdateUserRequest
import com.kirara.core.ui.template.MainTemplate
import com.kirara.core.ui.widget.PasswordField
import com.kirara.core.ui.widget.RegularField
import com.kirara.core.util.Dimens
import com.kirara.features.change_password.ChangePasswordViewModel

@Composable
fun ChangePasswordContent(
    viewModel: ChangePasswordViewModel,
    navigateBack: () -> Unit
){
    MainTemplate(
        topBar = {
            TopAppBar(
                title = {
                    Text("Change Password")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        }
    ) {

        val changePasswordRequest = remember { mutableStateOf(ChangePasswordRequest()) }

        val currentPassword = remember { mutableStateOf("") }
        val newPassword = remember { mutableStateOf("") }
        val newPasswordConfirmation = remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        changePasswordRequest.value = ChangePasswordRequest(
            currentPassword = currentPassword.value,
            newPassword = newPassword.value,
            newPasswordConfirmation = newPasswordConfirmation.value
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Current Password",
                    fontSize = Dimens.sp12,
                    modifier = Modifier.padding(bottom = Dimens.dp4)
                )
                PasswordField(
                    hint = stringResource(id = R.string.password_hint),
                    onPasswordChange = { currentPassword.value = it }
                )
                Spacer(modifier = Modifier.height(Dimens.dp16))
                Text(
                    text = "New Password",
                    fontSize = Dimens.sp12,
                    modifier = Modifier.padding(bottom = Dimens.dp4)
                )
                PasswordField(
                    hint = stringResource(id = R.string.password_hint),
                    onPasswordChange = { newPassword.value = it }
                )
                Spacer(modifier = Modifier.height(Dimens.dp16))
                Text(
                    text = "New Password Confirmation",
                    fontSize = Dimens.sp12,
                    modifier = Modifier.padding(bottom = Dimens.dp4)
                )
                PasswordField(
                    hint = stringResource(id = R.string.password_hint),
                    onPasswordChange = { newPasswordConfirmation.value = it },
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
            }
            Spacer(modifier = Modifier.height(Dimens.dp24))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    focusManager.clearFocus()
                    viewModel.changePasswordApiCall(changePasswordRequest.value)
                },
            ) {
                Text(stringResource(id = R.string.update))
            }
        }
    }
}