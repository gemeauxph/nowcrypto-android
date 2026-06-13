package io.nowcrypto.library.domain.payment

import io.nowcrypto.library.data.di.payment.PaymentRepository
import io.nowcrypto.library.remote.payment.PaymentResponse

class PayViaCryptoUseCase(
    private val repository: PaymentRepository
) {
    suspend fun execute(deviceId: String, apiKey: String, paymentRequestToken: String): PaymentResponse {
        return repository.payViaCrypto(deviceId, apiKey, paymentRequestToken)
    }
}