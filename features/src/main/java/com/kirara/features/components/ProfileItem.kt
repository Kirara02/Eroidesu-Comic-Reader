package com.kirara.features.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirara.core.util.Dimens


@Composable
fun ProfileItem(
    icon: Any?,
    title: String,
    onClickAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClickAction()
            },

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
                    .padding(8.dp),
            ) {
                when (icon) {
                    is ImageVector -> {
                        Icon(
                            icon,
                            contentDescription = null,
                            modifier = Modifier.size(22.dp, 22.dp)
                        )
                    }
                    is Painter -> {
                        Image(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier.size(22.dp, 22.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(Dimens.dp20))
            Text(
                title,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(32.dp, 32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .height(0.5.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        )
    }
}