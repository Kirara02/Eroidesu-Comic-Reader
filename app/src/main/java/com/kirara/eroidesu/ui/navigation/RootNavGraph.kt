package com.kirara.eroidesu.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kirara.core.ui.template.MainTemplate
import com.kirara.core.util.Graph
import com.kirara.features.navigation.GeneralScreen
import com.kirara.features.navigation.auth.AuthScreenRoute
import com.kirara.features.navigation.main.BottomNav
import com.kirara.features.navigation.main.MainHomeScreen
import com.kirara.features.splash.SplashScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = GeneralScreen.Splash.route,
    ) {
        composable(GeneralScreen.Splash.route) {
            MainTemplate {
                SplashScreen(
                    navigateToLogin = {
                        navController.navigate(AuthScreenRoute.LoginUser.route) {
                            popUpTo(Graph.ROOT) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToHome = {
                        navController.navigate(Graph.MAIN) {
                            popUpTo(Graph.ROOT) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        authNavGraph(navController = navController)
        composable(route = Graph.MAIN) {
            MainHomeScreen(
                modifier = Modifier,
                onUserLogout = {
                    navController.navigate(route = Graph.AUTH) {
                        popUpTo(Graph.ROOT) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
