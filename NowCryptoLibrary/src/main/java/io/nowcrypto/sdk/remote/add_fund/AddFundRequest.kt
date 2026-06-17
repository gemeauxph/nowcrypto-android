package io.nowcrypto.sdk.remote.add_fund

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class AddFundRequest(
    val publicKey: String,
    val transactionId: String,
    val paymentRequestToken: String,
    val walletAddress: String
)