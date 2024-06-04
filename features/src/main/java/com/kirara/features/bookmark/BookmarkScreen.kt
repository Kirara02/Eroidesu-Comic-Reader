package com.kirara.features.bookmark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kirara.core.ui.template.MainTemplate
import com.kirara.features.bookmark.section.BookmarkContent
import com.kirara.features.home.HomeViewModel

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    MainTemplate(
        modifier = modifier
    ) {
        BookmarkContent(
            modifier = modifier.fillMaxSize(),
            navigateToDetail = navigateToDetail,
            viewModel = viewModel
        )
    }
}