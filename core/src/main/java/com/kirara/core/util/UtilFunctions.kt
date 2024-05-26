package com.kirara.core.util

import android.util.Log
import com.kirara.core.BuildConfig
import java.text.NumberFormat
import java.util.Locale


object UtilFunctions {
    private val localeID = Locale("in", "ID")

    fun logE(message: String) {
        if (BuildConfig.DEBUG) Log.e("ERROR_IMO", message)
    }

}