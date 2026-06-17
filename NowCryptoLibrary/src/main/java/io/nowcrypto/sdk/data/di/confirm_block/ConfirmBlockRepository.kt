package io.nowcrypto.sdk.data.di.confirm_block

import io.nowcrypto.sdk.remote.confirm_block.ConfirmBlockResponse

interface ConfirmBlockRepository {
    suspend fun getConfirmedBlocksCount(
        transactionId: String
    ): ConfirmBlockResponse
}