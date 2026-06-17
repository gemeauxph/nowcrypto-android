package io.nowcrypto.sdk.remote.login

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LoginRequest(
    val username: String,
    val password: String,
)