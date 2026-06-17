package io.nowcrypto.sdk.data.di.register

import io.nowcrypto.sdk.remote.register.RegisterResponse

interface RegisterRepository {
    suspend fun register(
        username: String,
        email: String,
        password: String,
        deviceId: String
    ): RegisterResponse
}