package io.nowcrypto.sdk.remote.payment_request_token

import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Keep
@Serializable
data class SubRequestTokenResponse(
    val success: Boolean,
    val message: String,
    val status: String,
    @SerialName("payment_request_token")
    val paymentRequestToken: String
)