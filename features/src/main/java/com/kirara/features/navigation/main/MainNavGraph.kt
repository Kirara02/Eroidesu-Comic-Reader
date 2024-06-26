package com.kirara.features.navigation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kirara.core.R
import com.kirara.core.ui.template.MainTemplate
import com.kirara.core.util.Graph
import com.kirara.features.bookmark.BookmarkScreen
import com.kirara.features.change_password.ChangePasswordScreen
import com.kirara.features.detail_manga.DetailMangaScreen
import com.kirara.features.edit_profile.EditProfileScreen
import com.kirara.features.home.HomeScreen
import com.kirara.features.manga_chapter.MangaChapterScreen
import com.kirara.features.navigation.GeneralScreen
import com.kirara.features.navigation.main.model.BottomBarScreen
import com.kirara.features.profile.ProfileScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onUserLogout: () -> Unit
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(
                navigateToDetail = { mangaId ->
                    navController.navigate(GeneralScreen.DetailManga.createRoute(mangaId))
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
            BookmarkScreen(
                navigateToDetail = {mangaId ->
                    navController.navigate(GeneralScreen.DetailManga.createRoute(mangaId))
                }
            )
        }
        composable(
            GeneralScreen.DetailManga.route,
            arguments = listOf(navArgument("mangaId") { type = NavType.IntType }),
        ){
            val id = it.arguments?.getInt("mangaId") ?: -1
            DetailMangaScreen(
                mangaId = id,
                navigateBack = {
                    navController.navigateUp()
                },
                navigateToChapter = { chapterId ->
                    navController.navigate(GeneralScreen.DetailChapter.createRoute(chapterId))
                }
            )
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(
                navigateToEditProfile = {
                    navController.navigate(GeneralScreen.EditProfile.route)
                },
                navigateToChangePassword  = {
                    navController.navigate(GeneralScreen.ChangePassword.route)
                },
                onUserLogout =  onUserLogout
            )
        }
        composable(GeneralScreen.EditProfile.route) {
            EditProfileScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateOnSuccess = {
                    navController.navigate(Graph.MAIN)
                }
            )
        }
        composable(GeneralScreen.ChangePassword.route) {
            ChangePasswordScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateOnSuccess = {
                    navController.navigate(Graph.MAIN)
                }
            )
        }
        composable(
            GeneralScreen.DetailChapter.route,
            arguments = listOf(navArgument("chapterId") { type = NavType.IntType }),
        ) {
            val id = it.arguments?.getInt("chapterId") ?: -1
            MangaChapterScreen(
                chapterId = id,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}
