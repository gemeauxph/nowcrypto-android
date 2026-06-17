package io.nowcrypto.sdk.data.di.payment_request_token

import io.nowcrypto.sdk.remote.payment_request_token.SubRequestTokenResponse

interface SubRequestTokenRepository {
    suspend fun getSubscriptionRequestToken(
        secretKey: String,
        amount: Double,
        currency: String,
        network: String,
        subId: String
    ): SubRequestTokenResponse
}