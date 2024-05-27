package com.kirara.core.util

import android.content.Context
import android.net.Uri

object FileUtil {
    fun getRealPathFromURI(context: Context, uri: Uri): String? {
        return when (uri.scheme) {
            "content" -> {
                context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    val columnIndex = cursor.getColumnIndexOrThrow("_data")
                    cursor.moveToFirst()
                    cursor.getString(columnIndex)
                }
            }
            "file" -> {
                uri.path
            }
            else -> null
        }
    }
}
