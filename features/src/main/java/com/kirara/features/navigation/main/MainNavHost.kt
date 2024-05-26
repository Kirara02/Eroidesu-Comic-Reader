package com.kirara.features.navigation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kirara.core.R
import com.kirara.core.ui.template.MainTemplate
import com.kirara.features.detail_manga.DetailMangaScreen
import com.kirara.features.home.HomeScreen
import com.kirara.features.navigation.main.model.BottomBarScreen
import com.kirara.features.navigation.GeneralScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(
                navigateToDetail = { mangaId ->
                    navController.navigate(GeneralScreen.DetailManga.createRoute(mangaId ?: -1))
                }
            )
        }
        composable(BottomBarScreen.Manga.route) {
            MainTemplate {
                Text(
                    text = stringResource(id = R.string.manga),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        composable(BottomBarScreen.BookMark.route) {
            MainTemplate {
                Text(
                    text = stringResource(id = R.string.bookmark),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        composable(BottomBarScreen.Profile.route) {
            MainTemplate {
                Text(
                    text = stringResource(id = R.string.profile),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}