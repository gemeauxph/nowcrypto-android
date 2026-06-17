package io.nowcrypto.sdk.data.di.payment_status

import io.nowcrypto.sdk.remote.payment_status.PaymentStatusApi
import io.nowcrypto.sdk.remote.payment_status.PaymentStatusRequest
import io.nowcrypto.sdk.remote.payment_status.PaymentStatusResponse

class PaymentStatusRepositoryImpl(
    private val api: PaymentStatusApi
) : PaymentStatusRepository {
    override suspend fun getPaymentStatus(publicKey: String, paymentRequestToken: String?, trxId: String?): PaymentStatusResponse {
        return api.getPaymentStatus(PaymentStatusRequest(publicKey, paymentRequestToken, trxId))
    }
}