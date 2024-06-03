package com.kirara.core.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.kirara.core.util.Converters

@Entity(tableName = "table_manga", primaryKeys = ["id"])
data class MangaEntity(
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String? = null,

    @ColumnInfo(name = "genre")
    @TypeConverters(Converters::class)
    val genre: List<String?>? = null,

    @ColumnInfo(name = "type")
    val type: String? = null,
)
