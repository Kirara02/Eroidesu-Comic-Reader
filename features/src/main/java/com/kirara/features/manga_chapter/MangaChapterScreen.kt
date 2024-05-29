package com.kirara.features.manga_chapter

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kirara.core.data.UiState
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.ChapterImage
import com.kirara.core.util.UrlHelper
import kotlin.math.log

@Composable
fun MangaChapterScreen(
    chapterId: Int,
    navigateBack: () -> Unit,
    viewModel: MangaChapterViewModel = hiltViewModel()
) {
    val uiStateChapter by remember { viewModel.uiStateChapter }.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chapter Detail") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            when(uiStateChapter){
                is UiState.Initial -> {
                    Box(modifier = Modifier)
                }

                is UiState.Loading -> {
                    viewModel.getMangaChapterByIdApiCall(chapterId)
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    val chapter = (uiStateChapter as UiState.Success<BaseResponse<List<ChapterImage>>>).data.data

                    chapter.let {

                        LazyColumn{
                            items(it){ item ->
                                SubcomposeAsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(UrlHelper.formatProfileUrl(item.image))
                                        .crossfade(true)
                                        .build(),
                                    error = { error ->
                                        Box(
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(text = "${item.alt}\n${error.result.throwable.message}", textAlign = TextAlign.Center)
                                        }
                                    },
                                    contentDescription = item.alt,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp)
                                )
                            }
                        }
                    }
                }

                is UiState.Error -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Cannot load this page")
                    }
                }
            }
        }
    }
}