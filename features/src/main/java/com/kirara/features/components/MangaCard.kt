package com.kirara.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kirara.core.data.model.response.Manga
import com.kirara.core.ui.theme.textColor
import com.kirara.core.util.Dimens

@Composable
fun MangaCard(
    item: Manga,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(Dimens.dp8))
    ) {
        Column(
            modifier = Modifier
                .width(115.dp)
                .height(175.dp)
                .clip(RoundedCornerShape(Dimens.dp8))
                .background(Color.White)
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
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.height(116.dp)
                )
            }
            Spacer(modifier = Modifier.height(Dimens.dp10))
            Text(
                item.name ?: "-",
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
        }
    }
}

@Preview
@Composable
fun MangaPre(){
//    MangaCard(item = DummyData.dummyMangas.get(0))
}