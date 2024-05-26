package com.kirara.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kirara.core.data.model.response.Manga
import com.kirara.core.util.Dimens

@Composable
fun MangaHorizontalCard(
    item: Manga,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .padding(bottom = 12.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .fillMaxWidth()
                .padding(all = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(Dimens.dp8)
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.thumbnail)
                        .crossfade(true)
                        .build(),
                    loading = {
                        CircularProgressIndicator(
                            color = Color.LightGray,
                            modifier = Modifier.padding(48.dp)
                        )
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .height(68.dp)
                        .width(68.dp),
                    contentScale = ContentScale.FillWidth,
                )
            }
            Spacer(modifier = Modifier.width(36.dp))
            Column(
                modifier = Modifier.weight(1f),
            ){
                Text(
                    text = item.name ?: "-",
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.height(6.dp))
                item.genre?.let {
                    Text(
                        it.joinToString(", "),
                        color = Color(0xff939393),
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .padding(0.dp)
                ) {
                    Text(
                        "Chapter 1",
                        fontSize = 12.sp,
                        color = Color(0xFFFF6905),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFFFEBF0))
                            .padding(vertical = 4.dp, horizontal = 6.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MangaVerPre(){
//    MangaHorizontalCard(item = DummyData.dummyMangas.get(0))
}