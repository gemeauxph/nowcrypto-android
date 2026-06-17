package io.nowcrypto.sdk.remote.currency

import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Keep
@Serializable
data class CurrencyResponse(
    val success: Boolean,
    val message: String,
    val status: String,
    @SerialName("supported_currencies")
    val supportedCurrencies: List<String>
)
