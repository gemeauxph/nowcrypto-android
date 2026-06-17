package io.nowcrypto.sdk.domain.balance

import io.nowcrypto.sdk.data.di.balance.BalanceRepository
import io.nowcrypto.sdk.remote.balance.BalanceResponse

class FetchUserBalanceUseCase(
    private val repository: BalanceRepository
) {
    suspend fun execute(apiKey: String, currencyCode: String): BalanceResponse {
        return repository.fetchUserBalance(apiKey, currencyCode)
    }
}