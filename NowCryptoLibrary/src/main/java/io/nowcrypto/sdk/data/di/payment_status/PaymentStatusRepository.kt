package io.nowcrypto.sdk.data.di.payment_status

import io.nowcrypto.sdk.remote.payment_status.PaymentStatusResponse

interface PaymentStatusRepository {
    suspend fun getPaymentStatus(
        publicKey: String,
        paymentRequestToken: String?,
        trxId: String?
    ): PaymentStatusResponse
}