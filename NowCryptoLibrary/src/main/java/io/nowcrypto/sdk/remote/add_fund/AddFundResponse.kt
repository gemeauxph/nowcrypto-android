package io.nowcrypto.sdk.remote.add_fund

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class AddFundResponse(
    val success: Boolean,
    val message: String,
    val status: String? = null,
)