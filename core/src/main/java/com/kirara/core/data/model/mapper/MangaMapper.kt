package com.kirara.core.data.model.mapper

import com.kirara.core.data.datasource.local.entity.MangaEntity
import com.kirara.core.data.model.response.Manga

object MangaMapper {
    fun mapFromProductToEntity(manga: Manga) =
        MangaEntity(manga.id, manga.name, manga.thumbnail, manga.genre, manga.type)
}