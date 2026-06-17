package io.nowcrypto.sdk.data.di.payment_request_token

import io.nowcrypto.sdk.remote.payment_request_token.PaymentRequestTokenResponse

interface PaymentRequestTokenRepository {
    suspend fun getPaymentRequestToken(
        secretKey: String,
        amount: Double,
        currency: String,
        network: String
    ): PaymentRequestTokenResponse
}