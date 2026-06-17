package io.nowcrypto.sdk.domain.payment_request_token

import io.nowcrypto.sdk.data.di.payment_request_token.PaymentRequestTokenRepository
import io.nowcrypto.sdk.remote.payment_request_token.PaymentRequestTokenResponse

class PaymentRequestTokenUseCase(
    private val repository: PaymentRequestTokenRepository
) {
    suspend fun execute(secretKey: String, amount: Double, currency: String, network: String): PaymentRequestTokenResponse {
        return repository.getPaymentRequestToken(secretKey, amount, currency, network)
    }
}