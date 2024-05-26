package com.kirara.features.navigation.main.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class BottomBar (
    val route: String,
    @StringRes val titleResId: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconFocused: Int
)
