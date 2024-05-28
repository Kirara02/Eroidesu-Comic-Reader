package com.kirara.features.detail_manga.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailMangaSynopsis(desc: String) {
    var expanded by remember { mutableStateOf(false) }
    val textExceedsLimit = desc.length > 150

    val hide = if (expanded) "Show Some" else "Show More"
    val visibleText = if (expanded) desc else desc.take(150)

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 12.sp, color = Color(0xff9393A3))){
            append(visibleText)
            append("...")
        }
        if (textExceedsLimit) {
            withStyle(style = SpanStyle(color = Color.Blue, fontSize = 12.sp, fontWeight = FontWeight.Medium)) {
                pushStringAnnotation(tag = "toggle", annotation = hide)
                append(hide)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 20.dp, end = 20.dp),
    ) {
        Text(
            text = "Synopsis",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        ClickableText(text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.let { span ->
                    if (span.tag == "toggle") {
                        expanded = !expanded
                    }
                }
        })
//        Box {
//            Text(
//                text = desc,
//                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
//                maxLines = if (expanded) Int.MAX_VALUE else 3,
//                overflow = TextOverflow.Ellipsis,
//                color = Color(0xff9393A3),
//            )
//            if (textExceedsLimit && !expanded) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(60.dp)
//                        .background(
//                            brush = Brush.verticalGradient(
//                                colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background),
//                                startY = 0f,
//                                endY = 100f
//                            )
//                        )
//                )
//            }
//        }
//        if (textExceedsLimit) {
//            IconButton(
//                modifier = Modifier.align(Alignment.CenterHorizontally),
//                onClick = { expanded = !expanded },
//            ) {
//                Icon(
//                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
//                    contentDescription = "Expand"
//                )
//            }
//        }
    }
}
