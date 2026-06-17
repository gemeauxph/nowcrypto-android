package io.nowcrypto.sdk.remote.device_id

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class DeviceRequest(val deviceId: String, val apiKey: String, val paymentRequestToken: String)