package io.nowcrypto.sdk.remote.device_id

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RegisterDeviceResponse(
    val success: Boolean,
    val message: String,

    val supportedCurrencies: List<String> = emptyList(),

    val walletAddress: String? = null,
    val qrCode: String? = null,
    val balance: String? = null,
    val amount: String? = null,
    val currency: String? = null,
    val network: String? = null,
    val token: String? = null,

    val isSubscription: Boolean? = null,
    val period: String? = null,

    val environment: String = "",
    val unixTimeStamp: Long = 0,

    val userName: String? = null,
    val profilePictureUrl: String? = null
)