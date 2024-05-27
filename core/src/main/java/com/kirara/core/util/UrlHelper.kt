package com.kirara.core.util

import com.kirara.core.BuildConfig

object UrlHelper {

    fun formatProfileUrl(profileUrl: String): String {
        // Replace all occurrences of "\/" with "/"
        val formattedProfileUrl = profileUrl.replace("\\/", "/")

        // Construct the full URL using the formatted profile URL
        return "${BuildConfig.WEB_SERVICE_URL}storage/$formattedProfileUrl"
    }
}
