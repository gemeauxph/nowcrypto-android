package io.nowcrypto.sdk.remote.subscription

import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Keep
@Serializable
data class QueryActiveSubscriptionResponse(
    val success: Boolean,
    val message: String,
    val status: String,

    @SerialName("has_active_subscription")
    val hasActiveSubscription: Boolean,

    val subscriptions: List<NowCryptoSubscription>
)

@Keep
@Serializable
data class NowCryptoSubscription(
    @SerialName("trx_id")
    val trxId: String?,

    val status: String,
    val expiration: String
)

@Keep
@Serializable
data class ActiveSubscriptionResult(
    val hasActiveSubscription: Boolean,
    val subscriptions: List<NowCryptoSubscription>
)