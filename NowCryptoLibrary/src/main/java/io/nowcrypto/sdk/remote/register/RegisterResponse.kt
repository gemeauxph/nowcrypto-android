package io.nowcrypto.sdk.remote.register

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val status: String?,
    val token: String?,
    val userName: String?,
    val profilePictureUrl: String?
)