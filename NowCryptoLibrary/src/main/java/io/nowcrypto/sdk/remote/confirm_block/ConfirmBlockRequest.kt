package io.nowcrypto.sdk.remote.confirm_block

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ConfirmBlockRequest(val transactionId: String)