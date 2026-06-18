package io.nowcrypto.sdk.remote.login

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class LoginResponse(
    val success: Boolean,
    val message: String,
    val status: String? = null,
    val token: String? = null,
    val userName: String?,
    val profilePictureUrl: String?
)