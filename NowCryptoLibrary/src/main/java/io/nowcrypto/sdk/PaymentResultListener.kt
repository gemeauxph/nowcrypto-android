package io.nowcrypto.sdk

interface PaymentResultListener {
    fun onSuccess(transactionId: String)
    fun onFailure(errorMessage: String)
    fun onCancelled()
}
