package com.kirara.core.util

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

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

    fun getFileFromUri(context: Context, uri: Uri): File {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val tempFile = File.createTempFile("temp", null, context.cacheDir)
        tempFile.deleteOnExit()
        FileOutputStream(tempFile).use { outputStream ->
            inputStream?.copyTo(outputStream)
        }
        inputStream?.close()
        return tempFile
    }
}
