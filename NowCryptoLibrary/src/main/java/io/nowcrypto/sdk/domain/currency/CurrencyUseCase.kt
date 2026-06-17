package io.nowcrypto.sdk.domain.currency

import io.nowcrypto.sdk.data.di.currency.CurrencyRepository
import io.nowcrypto.sdk.remote.currency.CurrencyResponse

class CurrencyUseCase(
    private val repository: CurrencyRepository
) {
    suspend fun execute(
        publicKey: String
    ): CurrencyResponse {
        return repository.getSupportedCurrencies(
            publicKey
        )
    }
}