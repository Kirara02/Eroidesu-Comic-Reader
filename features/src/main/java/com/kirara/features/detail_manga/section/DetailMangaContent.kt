package com.kirara.features.detail_manga.section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kirara.core.data.UiState
import com.kirara.core.data.model.response.Manga
import com.kirara.features.detail_manga.DetailMangaViewModel

@Composable
fun DetailMangaContent(
    mangaId: Int,
    viewModel: DetailMangaViewModel,
) {
    val uiStateManga by remember { viewModel.uiStateManga }.collectAsState()

    when(uiStateManga) {
        is UiState.Initial -> {

        }

        is UiState.Loading -> {
            viewModel.getMangaByIdApiCall(mangaId)
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val manga = (uiStateManga as UiState.Success<Manga>).data
            Text("${manga.name}")
        }

        is UiState.Error -> {
            Text("error")
        }
    }
}
