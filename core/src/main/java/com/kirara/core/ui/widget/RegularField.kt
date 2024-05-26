package com.kirara.core.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.TextFieldValue
import com.kirara.core.util.Dimens

@Composable
fun RegularField(
    value: String = "",
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onTextChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions()
){
    var text by remember { mutableStateOf(TextFieldValue(value)) }

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
            value = text,
            onValueChange = {
                text = it
                onTextChange(it.text)
            },
            textStyle = TextStyle(
                fontSize = Dimens.sp12,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            keyboardActions = keyboardActions,
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
                    Text(
                        text = hint,
                        fontSize = Dimens.sp12,
                        color = Color.DarkGray,
                    )
                }
                innerTextField()
            },
            singleLine = true
        )
    }
}