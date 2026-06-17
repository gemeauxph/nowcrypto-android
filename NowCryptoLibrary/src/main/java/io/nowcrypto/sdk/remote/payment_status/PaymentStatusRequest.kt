package io.nowcrypto.sdk.remote.payment_status

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PaymentStatusRequest(
    val publicKey: String,
    val paymentRequestToken: String? = null,
    val trxId: String? = null
)