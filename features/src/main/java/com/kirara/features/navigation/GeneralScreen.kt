package com.kirara.features.navigation

sealed class GeneralScreen(val route: String){

    object Splash : GeneralScreen(route = "splash")

    object DetailManga : GeneralScreen(route = "home/{mangaId}"){
        fun createRoute(productId: Int) = "home/$productId"
    }

    object SearchManga : GeneralScreen(route = "home/search")

    object EditProfile : GeneralScreen(route = "profile/edit")
}