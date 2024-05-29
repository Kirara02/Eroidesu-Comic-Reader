package com.kirara.features.detail_manga.section

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kirara.core.data.model.response.Chapter

@Composable
fun ChapterCard(
    modifier: Modifier,
    chapter: Chapter
) {
    Box {
        Box(
            modifier = modifier
                .background(Color.White.copy(alpha = 0.7f))
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = chapter.chapter ?: "",
                            color = Color(0xff1F1F39)
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = chapter.rilis ?: "",
                            color = Color(0xff61BFAD)
                        )
                    }
                    Icon(
                        Icons.Rounded.KeyboardArrowRight,
                        contentDescription = null
                    )
                }
                Divider()
            }
        }
    }
}