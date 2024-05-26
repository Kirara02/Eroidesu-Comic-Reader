package com.kirara.features.login.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirara.core.data.model.request.LoginRequest
import com.kirara.core.ui.widget.PasswordField
import com.kirara.core.ui.widget.RegularField
import com.kirara.core.util.Dimens
import com.kirara.core.R

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onButtonActionClicked: (LoginRequest) -> Unit,
    navigateToRegister: () -> Unit,
) {
    val loginRequest = remember { mutableStateOf(LoginRequest()) }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    loginRequest.value = LoginRequest(email = email.value, password = password.value)

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
                onButtonActionClicked(loginRequest.value)
            },
        ) {
            Text(stringResource(id = R.string.login))
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.not_have_account))
            TextButton(
                onClick = navigateToRegister
            ) {
                Text(text = stringResource(id = R.string.register))
            }
        }
    }
}
