package io.nowcrypto.sdk.remote.register

import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("payment/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}