package com.kirara.features.bookmark.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirara.core.R
import com.kirara.core.data.UiState
import com.kirara.core.data.datasource.local.entity.MangaEntity
import com.kirara.core.data.model.response.Manga
import com.kirara.features.bookmark.BookmarkViewModel
import com.kirara.features.components.MangaHorizontalCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookmarkContent(
    modifier: Modifier,
    viewModel: BookmarkViewModel,
    navigateToDetail: (Int) -> Unit,
){
    val scrollState = rememberScrollState()
    val uiState by remember { viewModel.uiState }.collectAsState()

    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { viewModel.refresh() })

    Box(
        modifier = Modifier.fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.bookmark),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                IconButton(onClick = {

                }) {
                    Icon(Icons.Sharp.Search, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            when (uiState) {
                is UiState.Initial -> {

                }

                is UiState.Loading -> {
                    viewModel.getMangaDbCall()
                    Box(
                        modifier = Modifier
                            .fillMaxSize(), // Fill the parent's size
                        contentAlignment = Alignment.Center // Center the content
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    val mangas = (uiState as UiState.Success<MutableList<MangaEntity>>).data

                    if(mangas.isEmpty())
                        Box(
                            modifier = modifier.padding(horizontal = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.empty_manga),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    else
                        Column(
                            modifier = Modifier.padding(horizontal = 20.dp)
                        ) {
                            for(manga in mangas){
                                val item = Manga(
                                    id = manga.id,
                                    name = manga.name,
                                    thumbnail = manga.thumbnail,
                                    genre = manga.genre,
                                    type = manga.type
                                )

                                MangaHorizontalCard(
                                    item = item,
                                    modifier = Modifier.clickable {
                                        navigateToDetail(item.id ?: return@clickable)
                                    }
                                )
                            }
                        }
                }

                is UiState.Error -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.error_manga),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
