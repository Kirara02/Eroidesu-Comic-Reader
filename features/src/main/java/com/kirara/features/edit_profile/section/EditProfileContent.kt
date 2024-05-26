package com.kirara.features.edit_profile.section

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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.kirara.core.R
import com.kirara.core.data.model.request.LoginRequest
import com.kirara.core.data.model.request.UpdateUserRequest
import com.kirara.core.ui.template.MainTemplate
import com.kirara.core.ui.widget.PasswordField
import com.kirara.core.ui.widget.RegularField
import com.kirara.core.util.Dimens
import com.kirara.features.edit_profile.EditProfileViewModel

@Composable
fun EditProfileContent(
    viewModel: EditProfileViewModel,
    navigateBack: () -> Unit,
) {
    MainTemplate(
        topBar = {
            TopAppBar(
                title = {
                    Text("Edit Profile")
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
        val _name: String? by viewModel.name.collectAsState()
        val _email: String? by viewModel.email.collectAsState()

        val updateRequest = remember { mutableStateOf(UpdateUserRequest()) }

        val name = remember { mutableStateOf(_name ?: "") }
        val email = remember { mutableStateOf(_email ?: "") }
        val focusManager = LocalFocusManager.current

        updateRequest.value = UpdateUserRequest(email = email.value, name = name.value)


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
                    text = stringResource(id = R.string.name_form),
                    fontSize = Dimens.sp12,
                    modifier = Modifier.padding(bottom = Dimens.dp4)
                )
                RegularField(
                    value = name.value,
                    hint = stringResource(id = R.string.name_hint),
                    onTextChange = { name.value = it }
                )
                Spacer(modifier = Modifier.height(Dimens.dp16))
                Text(
                    text = stringResource(id = R.string.email_form),
                    fontSize = Dimens.sp12,
                    modifier = Modifier.padding(bottom = Dimens.dp4)
                )
                RegularField(
                    value = email.value,
                    hint = stringResource(id = R.string.email_hint),
                    onTextChange = { email.value = it },
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
                    viewModel.updateUserApiCall(updateRequest.value)
                },
            ) {
                Text(stringResource(id = R.string.update))
            }
        }
    }
}