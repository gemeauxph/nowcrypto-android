package io.nowcrypto.sdk.remote.payment_status

import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Keep
@Serializable
data class PaymentStatusResponse(
    val success: Boolean,
    val message: String,
    val status: String,
    val subscription: SubscriptionData? = null,
    val transaction: TransactionData? = null
)

@Keep
@Serializable
data class SubscriptionData(
    @SerialName("trx_id") val trxId: String,
    val status: String,
    val expiration: String? = null,
    val amount: String,
    val currency: String
)

@Keep
@Serializable
data class TransactionData(
    @SerialName("trx_id") val trxId: String,
    val status: String,
    val amount: String,
    val currency: String
)