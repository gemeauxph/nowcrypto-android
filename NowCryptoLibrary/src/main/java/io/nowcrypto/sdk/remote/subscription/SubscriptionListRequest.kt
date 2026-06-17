package io.nowcrypto.sdk.remote.subscription

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SubscriptionListRequest(
    val publicKey: String
)