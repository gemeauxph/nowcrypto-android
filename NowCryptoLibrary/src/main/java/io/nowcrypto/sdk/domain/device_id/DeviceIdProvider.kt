package io.nowcrypto.sdk.domain.device_id

interface DeviceIdProvider {
    fun getDeviceId(): String
}