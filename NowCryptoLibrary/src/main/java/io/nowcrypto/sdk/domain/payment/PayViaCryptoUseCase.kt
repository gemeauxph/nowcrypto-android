package io.nowcrypto.sdk.domain.payment

import io.nowcrypto.sdk.data.di.payment.PaymentRepository
import io.nowcrypto.sdk.remote.payment.PaymentResponse

class PayViaCryptoUseCase(
    private val repository: PaymentRepository
) {
    suspend fun execute(deviceId: String, apiKey: String, paymentRequestToken: String): PaymentResponse {
        return repository.payViaCrypto(deviceId, apiKey, paymentRequestToken)
    }
}