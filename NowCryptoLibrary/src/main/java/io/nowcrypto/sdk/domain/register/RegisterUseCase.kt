package io.nowcrypto.sdk.domain.register

import io.nowcrypto.sdk.data.di.register.RegisterRepository
import io.nowcrypto.sdk.remote.register.RegisterResponse

class RegisterUseCase(
    private val repository: RegisterRepository
) {
    suspend fun execute(username: String, email: String, password: String, deviceId: String): RegisterResponse {
        return repository.register(username, email, password, deviceId)
    }
}