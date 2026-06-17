package io.nowcrypto.sdk.remote.currency

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class CurrencyRequest(
    val publicKey: String
)