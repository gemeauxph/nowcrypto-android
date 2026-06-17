package io.nowcrypto.sdk.data.di.payment

import io.nowcrypto.sdk.remote.payment.PaymentApi
import io.nowcrypto.sdk.remote.payment.PaymentRequest
import io.nowcrypto.sdk.remote.payment.PaymentResponse
import io.nowcrypto.sdk.remote.payment.TransactionIdPaymentApi
import io.nowcrypto.sdk.remote.payment.TransactionIdPaymentRequest
import kotlin.String

class PaymentRepositoryImpl(
    private val paymentApi: PaymentApi,
    private val transactionIdPaymentApi: TransactionIdPaymentApi
) : PaymentRepository {
    override suspend fun payViaCrypto(deviceId: String, apiKey: String, paymentRequestToken: String): PaymentResponse {
        return paymentApi.payViaCrypto(PaymentRequest(deviceId, apiKey, paymentRequestToken ))
    }

    override suspend fun payViaTransactionId(
        deviceId: String,
        publicKey: String,
        transactionId: String,
        paymentRequestToken: String
    ): PaymentResponse {
        return transactionIdPaymentApi.payViaTransactionId(
            TransactionIdPaymentRequest(
                deviceId,
                publicKey,
                transactionId,
                paymentRequestToken
            )
        )
    }
}
