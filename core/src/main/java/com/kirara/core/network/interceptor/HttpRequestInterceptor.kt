package com.kirara.core.network.interceptor

//import com.kirara.core.util.TokenProvider
//import okhttp3.Interceptor
//import okhttp3.Response
//import javax.inject.Inject

//class AuthInterceptor @Inject constructor(
//    private val tokenProvider: TokenProvider
//) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//
//        val excludedUrls = listOf("auth/login", "auth/register")
//
//        if (excludedUrls.any { request.url.encodedPath.contains(it) }) {
//            return chain.proceed(request)
//        }
//
//        val token = tokenProvider.getToken()
//        val newRequest = request.newBuilder()
//            .addHeader("Authorization", "Bearer $token")
//            .build()
//
//        return chain.proceed(newRequest)
//    }
//}

import com.kirara.core.util.UtilFunctions.logE
import okhttp3.Interceptor
import okhttp3.Response

internal class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .addHeader("Accept","application/json")
            .url(originalRequest.url)
            .build()
        logE("HttpRequestInterceptor : $request")
        return chain.proceed(request)
    }
}
