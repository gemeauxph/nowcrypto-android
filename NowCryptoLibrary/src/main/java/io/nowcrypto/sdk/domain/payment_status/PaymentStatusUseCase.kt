package io.nowcrypto.sdk.domain.payment_status

import io.nowcrypto.sdk.data.di.payment_status.PaymentStatusRepository
import io.nowcrypto.sdk.remote.payment_status.PaymentStatusResponse

class PaymentStatusUseCase(
    private val repository: PaymentStatusRepository
) {
    suspend fun execute(publicKey: String, paymentRequestToken: String?, trxId: String?): PaymentStatusResponse {
        return repository.getPaymentStatus(publicKey, paymentRequestToken, trxId)
    }
}