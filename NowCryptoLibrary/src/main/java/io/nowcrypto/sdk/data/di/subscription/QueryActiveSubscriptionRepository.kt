package io.nowcrypto.sdk.data.di.subscription

import io.nowcrypto.sdk.remote.subscription.QueryActiveSubscriptionResponse

interface QueryActiveSubscriptionRepository {
    suspend fun queryActiveSubscription(
        publicKey: String,
        identifier: String
    ): QueryActiveSubscriptionResponse
}