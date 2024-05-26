package com.kirara.core.util

import androidx.annotation.DrawableRes
import com.kirara.core.R

data class DummyManga(
    val title: String,
    @DrawableRes val image: Int,
    val genres: List<String>
)

object DummyData {
    val dummyMangas = listOf<DummyManga>(
        DummyManga("Naruto", R.drawable.naruto, listOf("Shounen","Adventure","Comedy")),
        DummyManga("Attack on Titan", R.drawable.aot, listOf("Shounen","Adventure","Mistery")),
        DummyManga("Death Note", R.drawable.dn, listOf("Seinen","Mistery")),
        DummyManga("One Piece", R.drawable.op, listOf("Shounen","Advanture","Comedy")),
        DummyManga("Overlord", R.drawable.overlord, listOf("Shounen","Advanture","Isekai")),
        DummyManga("Sword Art Online", R.drawable.sao, listOf("Shounen","Advanture","Isekai")),
    )
}