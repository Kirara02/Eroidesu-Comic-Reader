package com.kirara.features.navigation

sealed class GeneralScreen(val route: String){

    object Splash : GeneralScreen(route = "splash")

    object DetailManga : GeneralScreen(route = "manga/{mangaId}"){
        fun createRoute(mangaId: Int) = "manga/$mangaId"
    }

    object DetailChapter : GeneralScreen(route = "chapter/{chapterId}"){
        fun createRoute(chapterId: Int) = "chapter/$chapterId"
    }

    object SearchManga : GeneralScreen(route = "home/search")

    object EditProfile : GeneralScreen(route = "profile/edit")
    object ChangePassword : GeneralScreen(route = "profile/change-password")
}