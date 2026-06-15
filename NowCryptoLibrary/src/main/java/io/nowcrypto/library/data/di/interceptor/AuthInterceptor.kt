package io.nowcrypto.library.data.di.interceptor

import io.nowcrypto.library.data.session.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sessionManager: SessionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // If an Authorization header is ALREADY present (like secret key), skip adding the session token
        if (originalRequest.header("Authorization") != null) {
            return chain.proceed(originalRequest)
        }

        // Otherwise, fetch the session token for normal authenticated requests
        val token = runBlocking {
            sessionManager.getToken()
        }

        val requestBuilder = originalRequest.newBuilder()
        if (!token.isNullOrEmpty()) {
            // Use .header() instead of .addHeader() as a safety measure to ensure uniqueness
            requestBuilder.header("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}