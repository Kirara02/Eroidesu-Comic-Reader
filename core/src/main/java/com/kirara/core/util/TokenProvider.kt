//package com.kirara.core.util
//
//import android.content.Context
//import dagger.hilt.android.qualifiers.ApplicationContext
//import javax.inject.Inject
//import javax.inject.Singleton
//
//interface TokenProvider {
//    fun getToken() : String
//}
//
//@Singleton
//class DefaultTokenProvider @Inject constructor(
//    @ApplicationContext private val context: Context
//) : TokenProvider {
//    override fun getToken(): String {
//        val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
//        return sharedPreferences.getString("auth_token", "") ?: ""
//    }
//}
