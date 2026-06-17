package io.nowcrypto.sdk.domain.subscription

import io.nowcrypto.sdk.data.di.subscription.SubscriptionListRepository
import io.nowcrypto.sdk.remote.subscription.SubscriptionListResponse

class SubscriptionListUseCase(
    private val repository: SubscriptionListRepository
) {
    suspend fun execute(publicKey: String): SubscriptionListResponse {
        return repository.getSubscriptionList(publicKey)
    }
}