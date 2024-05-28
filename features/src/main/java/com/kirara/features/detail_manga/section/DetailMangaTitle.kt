package com.kirara.features.detail_manga.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirara.core.data.model.response.Manga
import com.kirara.core.ui.theme.textColor

@Composable
fun DetailMangaTitle(
    manga: Manga
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = manga.name ?: "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Total Chapter: ${manga.totalChapter} Chapter",
                    color = textColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Author: ${manga.author}",
                    color = textColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Status: ${manga.status} Chapter",
                    color = textColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Genre: ${manga.genre?.joinToString(", ")}",
                    color = textColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                )
            }
            Spacer(modifier = Modifier.width(28.dp))
            Row {
                Icon(
                    Icons.Rounded.Star,
                    contentDescription = null,
                    tint = Color(0xffFF6905)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = manga.rating ?: "",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color(0xff61BFAD)
                )
            }
        }
    }
}