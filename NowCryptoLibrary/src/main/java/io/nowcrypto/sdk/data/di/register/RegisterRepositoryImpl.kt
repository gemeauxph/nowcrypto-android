package io.nowcrypto.sdk.data.di.register

import io.nowcrypto.sdk.remote.register.RegisterApi
import io.nowcrypto.sdk.remote.register.RegisterRequest
import io.nowcrypto.sdk.remote.register.RegisterResponse

class RegisterRepositoryImpl(
    private val api: RegisterApi
) : RegisterRepository {
    override suspend fun register(username: String, email: String, password: String, deviceId: String): RegisterResponse {
        return api.register(RegisterRequest(username, email, password, deviceId))
    }
}
