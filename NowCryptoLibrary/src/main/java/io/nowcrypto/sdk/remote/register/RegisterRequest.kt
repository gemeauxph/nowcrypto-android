package io.nowcrypto.sdk.remote.register

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val deviceId: String,
)