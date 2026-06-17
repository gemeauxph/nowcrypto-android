package io.nowcrypto.sdk.data.di.balance

import io.nowcrypto.sdk.remote.balance.BalanceResponse

interface BalanceRepository {
    suspend fun fetchUserBalance(apiKey: String, currencyCode: String): BalanceResponse
}