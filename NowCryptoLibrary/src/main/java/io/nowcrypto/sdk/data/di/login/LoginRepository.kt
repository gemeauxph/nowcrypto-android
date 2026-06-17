package io.nowcrypto.sdk.data.di.login

import io.nowcrypto.sdk.remote.login.LoginResponse

interface LoginRepository {
    suspend fun login(
        username: String,
        password: String
    ): LoginResponse
}