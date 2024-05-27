package com.kirara.core.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "app_prefs"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_PROFILE_URL = "profile_url"
    }

    private fun getPreferences(): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveAccessToken(token: String) {
        val editor = getPreferences().edit()
        editor.putString(KEY_ACCESS_TOKEN, token)
        editor.apply()
    }

    fun getAccessToken(): String? {
        return getPreferences().getString(KEY_ACCESS_TOKEN, null)
    }

    fun saveRefreshToken(token: String) {
        val editor = getPreferences().edit()
        editor.putString(KEY_REFRESH_TOKEN, token)
        editor.apply()
    }

    fun getRefreshToken(): String? {
        return getPreferences().getString(KEY_REFRESH_TOKEN, null)
    }

    fun clearAccessToken() {
        val editor = getPreferences().edit()
        editor.remove(KEY_ACCESS_TOKEN)
        editor.apply()
    }

    fun clearRefreshToken() {
        val editor = getPreferences().edit()
        editor.remove(KEY_REFRESH_TOKEN)
        editor.apply()
    }

    fun saveName(name: String) {
        val editor = getPreferences().edit()
        editor.putString(KEY_NAME, name)
        editor.apply()
    }

    fun getName(): String? {
        return getPreferences().getString(KEY_NAME, null)
    }

    fun saveEmail(email: String) {
        val editor = getPreferences().edit()
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    fun getEmail(): String? {
        return getPreferences().getString(KEY_EMAIL, null)
    }

    fun saveProfileUrl(url: String) {
        val editor = getPreferences().edit()
        editor.putString(KEY_PROFILE_URL, url)
        editor.apply()
    }

    fun getProfileUrl(): String? {
        return getPreferences().getString(KEY_PROFILE_URL, null)
    }

    fun clearProfileUrl() {
        val editor = getPreferences().edit()
        editor.remove(KEY_PROFILE_URL)
        editor.apply()
    }


    fun clearUserData() {
        val editor = getPreferences().edit()
        editor.remove(KEY_ACCESS_TOKEN)
        editor.remove(KEY_REFRESH_TOKEN)
        editor.remove(KEY_NAME)
        editor.remove(KEY_EMAIL)
        editor.remove(KEY_PROFILE_URL)
        editor.apply()
    }
}
