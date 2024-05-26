package com.kirara.eroidesu.ui.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kirara.core.util.Graph
import com.kirara.features.login.LoginScreen
import com.kirara.features.navigation.auth.AuthScreenRoute
import com.kirara.features.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreenRoute.LoginUser.route
    ) {
        composable(route = AuthScreenRoute.LoginUser.route) {
            LoginScreen(
                navigateToRegister = {
                    navController.navigate(route = AuthScreenRoute.RegisterUser.route)
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
        composable(route = AuthScreenRoute.RegisterUser.route) {
            RegisterScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
