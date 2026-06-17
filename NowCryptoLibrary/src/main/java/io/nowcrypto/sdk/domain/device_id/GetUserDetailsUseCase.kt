package io.nowcrypto.sdk.domain.device_id

import io.nowcrypto.sdk.data.di.device_id.DeviceRepository
import io.nowcrypto.sdk.remote.device_id.RegisterDeviceResponse

class GetUserDetailsUseCase(
    private val repository: DeviceRepository
) {
    suspend fun execute(apiKey: String, paymentRequestToken: String): RegisterDeviceResponse {
        return repository.getUserDetails(apiKey, paymentRequestToken)
    }
}