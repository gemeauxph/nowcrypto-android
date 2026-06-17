package io.nowcrypto.sdk.data.di.login

import io.nowcrypto.sdk.remote.login.LoginApi
import io.nowcrypto.sdk.remote.login.LoginRequest
import io.nowcrypto.sdk.remote.login.LoginResponse

class LoginRepositoryImpl(
    private val api: LoginApi
) : LoginRepository {
    override suspend fun login(username: String, password: String): LoginResponse {
        return api.login(LoginRequest(username, password))
    }
}
