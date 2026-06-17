package io.nowcrypto.sdk.data.di.balance

import io.nowcrypto.sdk.remote.balance.BalanceApi
import io.nowcrypto.sdk.remote.balance.BalanceRequest
import io.nowcrypto.sdk.remote.balance.BalanceResponse

class BalanceRepositoryImpl(
    private val api: BalanceApi
) : BalanceRepository {
    override suspend fun fetchUserBalance(apiKey: String, currencyCode: String): BalanceResponse {
        return api.fetchUserBalance(BalanceRequest(apiKey, currencyCode))
    }
}
