package io.nowcrypto.sdk.data.di.add_fund

import io.nowcrypto.sdk.remote.add_fund.AddFundResponse

interface AddFundRepository {

    suspend fun addFund(
        publicKey: String,
        transactionId: String,
        paymentRequestToken: String,
        walletAddress: String
    ): AddFundResponse
}