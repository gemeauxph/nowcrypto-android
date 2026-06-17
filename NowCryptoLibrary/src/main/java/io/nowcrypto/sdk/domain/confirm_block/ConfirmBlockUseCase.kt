package io.nowcrypto.sdk.domain.confirm_block

import io.nowcrypto.sdk.data.di.confirm_block.ConfirmBlockRepository
import io.nowcrypto.sdk.remote.confirm_block.ConfirmBlockResponse

class ConfirmBlockUseCase(
    private val repository: ConfirmBlockRepository
) {
    suspend fun execute(transactionId: String): ConfirmBlockResponse {
        return repository.getConfirmedBlocksCount(transactionId)
    }
}