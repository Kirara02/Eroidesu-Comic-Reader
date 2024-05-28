package com.kirara.features.home.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kirara.core.R
import com.kirara.core.data.UiState
import com.kirara.core.data.model.response.MangaResponse
import com.kirara.core.util.Dimens
import com.kirara.core.util.UrlHelper
import com.kirara.features.components.MangaCard
import com.kirara.features.components.MangaHorizontalCard
import com.kirara.features.home.HomeViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    val uiStateManga by remember { viewModel.uiStateManga }.collectAsState()
    val uiStatePopularManga by remember { viewModel.uiStatePopularManga }.collectAsState()

    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { viewModel.refresh() })

    val name by viewModel.name.collectAsState()
    val profileUrl by viewModel.profileUrl.collectAsState()

    val imageUrl = remember(profileUrl) {
        when {
            profileUrl != null -> UrlHelper.formatProfileUrl(profileUrl ?: "")
            else -> "https://ui-avatars.com/api/?name=$name"
        }
    }


    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = Dimens.dp16,
                    horizontal = Dimens.dp24
                )
//                .pullRefresh(pullRefreshState)
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier.size(58.dp),
                    shape = CircleShape,
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        loading = {
                            CircularProgressIndicator(
                                color = Color.LightGray,
                                modifier = Modifier.padding(48.dp)
                            )
                        },
                        contentDescription = stringResource(R.string.profile),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(Dimens.dp20))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        stringResource(id = R.string.greeting),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        name ?: "Guest",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(Dimens.dp24))
            TrendingManga(
                uiStateManga = uiStateManga,
                viewModel = viewModel,
                navigateToDetail = navigateToDetail
            )
            Spacer(modifier = Modifier.height(Dimens.dp24))
            UpdatedManga(
                uiStatePopularManga = uiStatePopularManga,
                viewModel = viewModel,
                navigateToDetail = navigateToDetail
            )
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun TrendingManga(
    uiStateManga: UiState<MangaResponse>,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
){
    Column {
        Text(
            text = "Trending Manga",
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
    }
    Spacer(modifier = Modifier.height(Dimens.dp16))
    when(uiStateManga){
        is UiState.Initial -> {

        }
        is UiState.Loading -> {
            viewModel.getMangasApiCall()
            Box(
                modifier = Modifier
                    .fillMaxSize(), // Fill the parent's size
                contentAlignment = Alignment.Center // Center the content
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val mangas = (uiStateManga as UiState.Success<MangaResponse>).data.data
            mangas?.let {
                LazyRow {
                    items(it) { item ->
                        MangaCard(
                            item = item,
                            modifier = Modifier.clickable {
                                navigateToDetail(item.id ?: return@clickable)
                            }
                        )
                    }
                }
            } ?: run {
                // Handle the null case if necessary, e.g., show a message or an empty state
                Text("No manga available")
            }
        }

        is UiState.Error -> {
            Text(text = stringResource(R.string.error_manga), color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
fun UpdatedManga(
    uiStatePopularManga: UiState<MangaResponse>,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
){
    Text(
        text = "Updated Manga",
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.height(Dimens.dp16))
    when(uiStatePopularManga){
        is UiState.Initial -> {

        }

        is UiState.Loading -> {
            viewModel.getPopularMangasApiCall()
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val mangas = (uiStatePopularManga as UiState.Success<MangaResponse>).data.data
            mangas?.let {
                Column {
                    for(item in it){
                        MangaHorizontalCard(
                            item = item,
                            modifier = Modifier
                                .clickable {
                                    navigateToDetail(item.id ?: return@clickable)
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                }
            } ?: run {
                Text("No manga available")
            }
        }

        is UiState.Error -> {
            Text(text = stringResource(R.string.error_manga), color = MaterialTheme.colorScheme.onSurface)
        }
    }

}

@Preview
@Composable
fun HomeContentPreview(){
    HomeContent(
        navigateToDetail = {

        }
    )
}

