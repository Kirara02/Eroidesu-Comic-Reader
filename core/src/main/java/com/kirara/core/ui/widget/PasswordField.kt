package com.kirara.core.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kirara.core.util.Dimens

@Composable
fun PasswordField(
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Next,
    onPasswordChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordVisibility by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .height(Dimens.dp40)
            .fillMaxWidth()
            .shadow(elevation = Dimens.dp3, shape = RoundedCornerShape(Dimens.dp8))
            .background(Color.White, RoundedCornerShape(Dimens.dp8))
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(horizontal = Dimens.dp24),
            value = password,
            onValueChange = {
                password = it
                onPasswordChange(it.text)
            },
            textStyle = TextStyle(
                fontSize = Dimens.sp12,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            decorationBox = { innerTextField ->
                if (password.text.isEmpty()) {
                    Text(
                        text = hint,
                        fontSize = Dimens.sp12,
                        color = Color.DarkGray,
                    )
                }
                innerTextField()
            },
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        )

        IconButton(
            onClick = { passwordVisibility = !passwordVisibility },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                contentDescription = if (passwordVisibility) "hide_password" else "show_password"
            )
        }
    }
}