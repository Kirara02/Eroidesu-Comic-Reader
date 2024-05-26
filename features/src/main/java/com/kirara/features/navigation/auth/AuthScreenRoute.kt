package com.kirara.features.navigation.auth


sealed class AuthScreenRoute (val route: String) {
    object LoginUser : AuthScreenRoute(route = "login")
    object RegisterUser : AuthScreenRoute(route = "register")
}