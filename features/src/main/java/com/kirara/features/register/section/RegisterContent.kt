package com.kirara.features.register.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirara.core.R
import com.kirara.core.data.model.request.RegisterRequest
import com.kirara.core.ui.widget.PasswordField
import com.kirara.core.ui.widget.RegularField
import com.kirara.core.util.Dimens

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    onButtonActionClicked: (RegisterRequest) -> Unit,
    navigateBack: () -> Unit,
) {
    val registerRequest = remember { mutableStateOf(RegisterRequest()) }
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    registerRequest.value = RegisterRequest(name = name.value, email = email.value, password = password.value)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Selamat Datang",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(Dimens.dp24))
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.name_form),
                fontSize = Dimens.sp12,
                modifier = Modifier.padding(bottom = Dimens.dp4)
            )
            RegularField(
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
                hint = stringResource(id = R.string.email_hint),
                onTextChange = { email.value = it }
            )
            Spacer(modifier = Modifier.height(Dimens.dp16))
            Text(
                text = stringResource(id = R.string.password_form),
                fontSize = Dimens.sp12,
                modifier = Modifier.padding(bottom = Dimens.dp4)
            )
            PasswordField(
                hint = stringResource(id = R.string.password_hint),
                onPasswordChange = { password.value = it },
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
                onButtonActionClicked(registerRequest.value)
            },
        ) {
            Text(stringResource(id = R.string.register))
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.already_have_account))
            TextButton(
                onClick = navigateBack
            ) {
                Text(text = stringResource(id = R.string.login))
            }
        }
    }
}