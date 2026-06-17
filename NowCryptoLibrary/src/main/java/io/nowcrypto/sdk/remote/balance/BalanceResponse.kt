package io.nowcrypto.sdk.remote.balance

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class BalanceResponse(
    val success: Boolean?,
    val message: String,
    val balance: String?,
)
