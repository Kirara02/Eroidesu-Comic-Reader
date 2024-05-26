package com.kirara.features.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kirara.features.navigation.main.model.BottomBarScreen

@Composable
fun MainHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onUserLogout: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigationItemContentList = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Manga,
        BottomBarScreen.BookMark,
        BottomBarScreen.Profile,
    )

    BottomNav(
        modifier = modifier,
        navigationItemContentList = navigationItemContentList,
        navController = navController,
        currentDestination = currentDestination,
        onUserLogout = onUserLogout
    )
}