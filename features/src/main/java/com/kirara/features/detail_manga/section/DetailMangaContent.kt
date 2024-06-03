package com.kirara.features.detail_manga.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirara.core.data.UiState
import com.kirara.core.data.model.response.BaseResponse
import com.kirara.core.data.model.response.Manga
import com.kirara.core.ui.theme.greyBG
import com.kirara.core.util.Extensions.myToast
import com.kirara.features.detail_manga.DetailMangaViewModel

@Composable
fun DetailMangaContent(
    mangaId: Int,
    viewModel: DetailMangaViewModel,
    navigateBack: () -> Unit,
    navigateToChapter: (Int) -> Unit
) {
    val context = LocalContext.current

    val uiStateManga by remember { viewModel.uiStateManga }.collectAsState()
    val hasSavedManga by remember { viewModel.hasSavedManga}.collectAsState()
    var manga by remember { mutableStateOf<Manga?>(null) }


    Scaffold(
        containerColor = greyBG,
        bottomBar = {
            Surface(
                shadowElevation = 10.dp,
                shape =  RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(vertical = 14.dp, horizontal = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xffFFEBF0))
                            .clickable {
                                manga?.let {
                                    if(hasSavedManga){
                                        context.myToast("Manga removed from favorites")
                                        viewModel.deleteMangaDb(it)
                                    }else{
                                        context.myToast("Manga added to favorites")
                                        viewModel.insertMangaDb(it)
                                    }
                                }
                            }
                            .padding(vertical = 10.dp, horizontal = 28.dp)
                    ) {
                        Icon(
                            imageVector = if (!hasSavedManga) Icons.Outlined.StarOutline else Icons.Default.Star,
                            null,
                            tint = Color(0xffFF6905)
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xff61BFAD))
                            .padding(vertical = 12.dp, horizontal = 28.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = "Baca Manga",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ){
                HandleUiState(
                    mangaId = mangaId,
                    uiStateManga = uiStateManga,
                    viewModel = viewModel,
                    navigateBack = navigateBack,
                    navigateToChapter = navigateToChapter,
                    onSuccess = { mangaData ->
                        manga = mangaData
                    }
                )
            }
        }
    )
}

@Composable
fun HandleUiState(
    mangaId: Int,
    uiStateManga: UiState<BaseResponse<Manga>>,
    viewModel: DetailMangaViewModel,
    navigateBack: () -> Unit,
    navigateToChapter: (Int) -> Unit,
    onSuccess: (Manga) -> Unit
) {
    val configuration = LocalConfiguration.current


    when(uiStateManga) {
        is UiState.Initial -> {
            Box(modifier = Modifier)
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
            val manga = uiStateManga.data.data
            onSuccess(manga)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                DetailMangaHeader(
                    manga = manga,
                    configuration = configuration,
                    navigateBack = navigateBack,
                )
                DetailMangaTitle(manga = manga)
                DetailMangaSynopsis(desc = manga.sinopsis ?: "")
                DetailMangaListChapter(viewModel = viewModel, mangaId = mangaId, navigateToChapter = navigateToChapter)
                Spacer(modifier = Modifier.height(12.dp))
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

