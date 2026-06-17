package io.nowcrypto.sdk.data.di.confirm_block

import io.nowcrypto.sdk.remote.confirm_block.ConfirmBlockApi
import io.nowcrypto.sdk.remote.confirm_block.ConfirmBlockResponse

class ConfirmBlockRepositoryImpl(
    private val api: ConfirmBlockApi
) : ConfirmBlockRepository {

    override suspend fun getConfirmedBlocksCount(
        transactionId: String,
    ): ConfirmBlockResponse {
        return api.getConfirmedBlocksCount(transactionId)
    }
}