package io.nowcrypto.sdk.domain.payment

import io.nowcrypto.sdk.data.di.payment.PaymentRepository
import io.nowcrypto.sdk.remote.payment.PaymentResponse

class PayViaTransactionIdUseCase(
    private val repository: PaymentRepository
) {
    suspend fun execute(
        deviceId: String,
        publicKey: String,
        transactionId: String,
        paymentRequestToken: String
    ): PaymentResponse {
        return repository.payViaTransactionId(
            deviceId,
            publicKey,
            transactionId,
            paymentRequestToken
        )
    }
}