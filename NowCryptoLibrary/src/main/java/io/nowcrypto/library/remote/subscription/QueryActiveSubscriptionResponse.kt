package io.nowcrypto.library.remote.subscription

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class QueryActiveSubscriptionResponse(
    val success: Boolean,
    val message: String,
    val status: String,

    @field:Json(name = "has_active_subscription")
    val hasActiveSubscription: Boolean,

    val subscriptions: List<NowCryptoSubscription>
)

@Keep
@JsonClass(generateAdapter = true)
data class NowCryptoSubscription(
    @field:Json(name = "trx_id")
    val trxId: String?,

    val status: String,
    val expiration: String
)

@Keep
data class ActiveSubscriptionResult(
    val hasActiveSubscription: Boolean,
    val subscriptions: List<NowCryptoSubscription>
)