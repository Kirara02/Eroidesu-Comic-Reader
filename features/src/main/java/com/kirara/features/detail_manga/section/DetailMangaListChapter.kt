package com.kirara.features.detail_manga.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kirara.core.data.UiState
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Chapter
import com.kirara.features.detail_manga.DetailMangaViewModel

@Composable
fun DetailMangaListChapter(
    mangaId: Int,
    viewModel: DetailMangaViewModel,
) {
    val uiStateChapter by remember { viewModel.uiStateChapter }.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
    ) {
        HandleUiState(uiStateChapter = uiStateChapter, viewModel = viewModel, mangaId= mangaId)
    }
}

@Composable
fun HandleUiState(
    uiStateChapter: UiState<BaseResponse<List<Chapter>>>,
    viewModel: DetailMangaViewModel,
    mangaId: Int
) {

    when(uiStateChapter){
        is UiState.Initial -> {

        }

        is UiState.Loading -> {
            viewModel.getMangaListChaptersApiCall(mangaId)
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val chapters = (uiStateChapter as UiState.Success<BaseResponse<List<Chapter>>>).data.data
            chapters.let {
                LazyColumn(
                    modifier =  Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .height(350.dp)
                ){
                    items(it){item ->
                        ChapterCard(chapter = item)
                    }
                }
            }
        }

        is UiState.Error -> {
            val error = (uiStateChapter as UiState.Error).errorMessage
            Text(text = error)
        }
    }
}