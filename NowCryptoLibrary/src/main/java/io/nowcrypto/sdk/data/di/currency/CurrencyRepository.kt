package io.nowcrypto.sdk.data.di.currency

import io.nowcrypto.sdk.remote.currency.CurrencyResponse

interface CurrencyRepository {
    suspend fun getSupportedCurrencies(
        publicKey: String
    ): CurrencyResponse
}