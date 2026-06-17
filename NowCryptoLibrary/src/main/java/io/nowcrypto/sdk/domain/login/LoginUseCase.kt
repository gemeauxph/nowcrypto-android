package io.nowcrypto.sdk.domain.login

import io.nowcrypto.sdk.data.di.login.LoginRepository
import io.nowcrypto.sdk.remote.login.LoginResponse

class LoginUseCase(
    private val repository: LoginRepository
) {
    suspend fun execute(username: String, password: String): LoginResponse {
        return repository.login(username, password)
    }
}