package io.nowcrypto.sdk.domain.subscription

import io.nowcrypto.sdk.data.di.subscription.QueryActiveSubscriptionRepository
import io.nowcrypto.sdk.remote.subscription.QueryActiveSubscriptionResponse

class QueryActiveSubscriptionUseCase(
    private val repository: QueryActiveSubscriptionRepository
) {
    suspend fun execute(publicKey: String, identifier: String): QueryActiveSubscriptionResponse {
        return repository.queryActiveSubscription(publicKey, identifier)
    }
}