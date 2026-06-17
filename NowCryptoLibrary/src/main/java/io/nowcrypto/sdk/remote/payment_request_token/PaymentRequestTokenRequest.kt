package io.nowcrypto.sdk.remote.payment_request_token

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PaymentRequestTokenRequest(
    val amount: Double,
    val currency: String,
    val network: String
)