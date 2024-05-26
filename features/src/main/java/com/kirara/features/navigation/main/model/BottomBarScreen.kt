package com.kirara.features.navigation.main.model

import com.kirara.core.R


sealed class BottomBarScreen(val route: String){
    object Home: BottomBar(
        route = "home",
        titleResId = R.string.home,
        icon = R.drawable.home,
        iconFocused = R.drawable.home
    )

    object Manga: BottomBar(
        route = "manga",
        titleResId = R.string.manga,
        icon = R.drawable.manga,
        iconFocused = R.drawable.manga
    )

    object BookMark: BottomBar(
        route = "bookmark",
        titleResId = R.string.bookmark,
        icon = R.drawable.bookmark,
        iconFocused = R.drawable.bookmark
    )

    object Profile: BottomBar(
        route = "profile",
        titleResId = R.string.profile,
        icon = R.drawable.profile,
        iconFocused = R.drawable.profile
    )
}