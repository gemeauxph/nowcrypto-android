package io.nowcrypto.sdk.data.di.subscription

import io.nowcrypto.sdk.remote.subscription.QueryActiveSubscriptionApi
import io.nowcrypto.sdk.remote.subscription.QueryActiveSubscriptionRequest
import io.nowcrypto.sdk.remote.subscription.QueryActiveSubscriptionResponse


class QueryActiveSubscriptionRepositoryImpl(
    private val api: QueryActiveSubscriptionApi
) : QueryActiveSubscriptionRepository {
    override suspend fun queryActiveSubscription(publicKey: String, identifier: String): QueryActiveSubscriptionResponse {
        return api.queryActiveSubscription(QueryActiveSubscriptionRequest(publicKey, identifier))
    }
}