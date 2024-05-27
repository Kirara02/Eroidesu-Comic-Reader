package com.kirara.features.navigation.main

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kirara.core.R
import com.kirara.core.ui.template.MainTemplate
import com.kirara.core.util.Graph
import com.kirara.features.change_password.ChangePasswordScreen
import com.kirara.features.edit_profile.EditProfileScreen
import com.kirara.features.home.HomeScreen
import com.kirara.features.navigation.GeneralScreen
import com.kirara.features.navigation.auth.AuthScreenRoute
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
    }
}
