package io.nowcrypto.sdk.remote.balance

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class BalanceRequest(val apiKey: String, val currencyCode: String)