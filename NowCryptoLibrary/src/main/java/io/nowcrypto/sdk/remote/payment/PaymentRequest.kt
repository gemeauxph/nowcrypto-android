package io.nowcrypto.sdk.remote.payment

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PaymentRequest(
    val deviceId: String,
    val apiKey: String,
    val paymentRequestToken: String,
)

@Keep
@Serializable
data class TransactionIdPaymentRequest(
    val deviceId: String,
    val publicKey: String,
    val transactionId: String,
    val paymentRequestToken: String
)