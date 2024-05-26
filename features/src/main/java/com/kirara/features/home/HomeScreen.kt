package com.kirara.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kirara.core.R
import com.kirara.core.ui.template.MainTemplate
import com.kirara.features.home.section.HomeContent

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel =  hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    MainTemplate(
        modifier = modifier,
    ) {
        HomeContent(
            modifier = modifier.fillMaxSize(),
            viewModel = viewModel,
            navigateToDetail = navigateToDetail,
        )
    }
}