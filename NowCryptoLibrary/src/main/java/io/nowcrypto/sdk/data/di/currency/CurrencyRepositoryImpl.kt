package io.nowcrypto.sdk.data.di.currency

import io.nowcrypto.sdk.remote.currency.CurrencyApi
import io.nowcrypto.sdk.remote.currency.CurrencyRequest
import io.nowcrypto.sdk.remote.currency.CurrencyResponse

class CurrencyRepositoryImpl(
    private val api: CurrencyApi
) : CurrencyRepository {
    override suspend fun getSupportedCurrencies(publicKey: String): CurrencyResponse {
        return api.getSupportedCurrencies(CurrencyRequest(publicKey))
    }
}
