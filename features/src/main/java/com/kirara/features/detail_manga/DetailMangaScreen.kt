package com.kirara.features.detail_manga

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.kirara.features.detail_manga.section.DetailMangaContent

@Composable
fun DetailMangaScreen(
    mangaId: Int,
    viewModel: DetailMangaViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ){
                DetailMangaContent(
                    mangaId = mangaId,
                    viewModel = viewModel
                )
            }
        }
    )
}