package io.nowcrypto.sdk.data.di.device_id

import io.nowcrypto.sdk.remote.device_id.RegisterDeviceResponse

interface DeviceRepository {
    suspend fun sendDeviceId(deviceId: String, apiKey: String, paymentRequestToken: String): RegisterDeviceResponse

    suspend fun getUserDetails(apiKey: String, paymentRequestToken: String): RegisterDeviceResponse
}