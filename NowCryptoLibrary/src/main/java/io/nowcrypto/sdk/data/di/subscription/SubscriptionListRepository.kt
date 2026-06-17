package io.nowcrypto.sdk.data.di.subscription

import io.nowcrypto.sdk.remote.subscription.SubscriptionListResponse

interface SubscriptionListRepository {
    suspend fun getSubscriptionList(
        publicKey: String
    ): SubscriptionListResponse
}