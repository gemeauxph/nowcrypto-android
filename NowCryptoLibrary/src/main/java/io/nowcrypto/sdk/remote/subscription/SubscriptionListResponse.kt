package io.nowcrypto.sdk.remote.subscription

import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Keep
@Serializable
data class SubscriptionListResponse(
    val success: Boolean,
    val message: String,
    val status: String,
    val subscriptions: List<NowCryptoSubscriptionItem>
)

@Keep
@Serializable
data class NowCryptoSubscriptionItem(
    @SerialName("sub_id") val subId: String,
    @SerialName("sub_name") val subName: String,
    val period: String
)