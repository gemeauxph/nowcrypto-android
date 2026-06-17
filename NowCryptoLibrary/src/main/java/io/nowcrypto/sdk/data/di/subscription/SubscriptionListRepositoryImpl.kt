package io.nowcrypto.sdk.data.di.subscription

import io.nowcrypto.sdk.remote.subscription.SubscriptionListApi
import io.nowcrypto.sdk.remote.subscription.SubscriptionListRequest
import io.nowcrypto.sdk.remote.subscription.SubscriptionListResponse

class SubscriptionListRepositoryImpl(
    private val api: SubscriptionListApi
) : SubscriptionListRepository {
    override suspend fun getSubscriptionList(publicKey: String): SubscriptionListResponse {
        return api.getSubscriptionList(SubscriptionListRequest(publicKey))
    }
}