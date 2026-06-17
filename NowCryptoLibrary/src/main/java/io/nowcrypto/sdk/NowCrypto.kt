package io.nowcrypto.sdk

import android.content.Context
import android.content.Intent
import android.util.Log
import io.nowcrypto.sdk.data.NowCryptoResult
import io.nowcrypto.sdk.presentation.MainActivity
import io.nowcrypto.sdk.remote.payment_status.PaymentStatusResponse
import io.nowcrypto.sdk.remote.subscription.NowCryptoSubscriptionItem
import io.nowcrypto.sdk.data.di.NowCryptoInternal
import io.nowcrypto.sdk.remote.subscription.ActiveSubscriptionResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Interface for receiving results from NowCrypto SDK in a Java-friendly way.
 */
fun interface NowCryptoCallback<T> {
    fun onResult(result: NowCryptoResult<T>)
}

/**
 * Interface for receiving results with separate Success and Error callbacks.
 * Recommended for Java users to avoid 'instanceof' checks.
 */
interface NowCryptoListener<T> {
    fun onSuccess(data: T)
    fun onError(message: String)
}

object NowCrypto {
    private var paymentResultListener: PaymentResultListener? = null
    private var _publicKey: String? = null

    /**
     * Initialize the library once with your public API key.
     * Best called in Application.onCreate() or your main entry Activity.
     */
    @JvmStatic
    fun init(publicKey: String) {
        this._publicKey = publicKey
    }

    /**
     * Helper to ensure API key exists before performing actions
     */
    private fun getRequiredPublicKey(): String {
        return _publicKey ?: throw IllegalStateException(
            "NowCrypto is not initialized. Please call NowCrypto.init(publicKey) before use."
        )
    }

    /**
     * Launch the library's internal Payment Activity.
     * API Key is retrieved from the stored state.
     */
    @JvmStatic
    fun launchPayment(
        context: Context,
        paymentRequestToken: String,
        listener: PaymentResultListener
    ) {
        val publicKey = getRequiredPublicKey()
        paymentResultListener = listener

        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(MainActivity.PAYMENT_REQUEST_TOKEN, paymentRequestToken)
            putExtra(MainActivity.API_KEY, publicKey)
        }
        context.startActivity(intent)
    }

    /**
    * Fetch supported currencies using the initialized API Key.
    */
    @JvmStatic
    fun getSupportedCurrencies(
        context: Context,
        callback: NowCryptoCallback<List<String>>
    ) {
        // Fail fast if the developer forgot to call NowCrypto.init()
        val publicKey = try {
            getRequiredPublicKey()
        } catch (e: IllegalStateException) {
            callback.onResult(NowCryptoResult.Error(message = e.message ?: "NowCrypto not initialized"))
            return
        }

        // Access internal dependencies
        val internal = NowCryptoInternal.getInstance(context)
        val currencyUseCase = internal.currencyUseCase

        // Launch background request
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Passing the stored publicKey to the UseCase
                val response = currencyUseCase.execute(publicKey)

                withContext(Dispatchers.Main) {
                    if (response.success) {
                        callback.onResult(NowCryptoResult.Success(response.supportedCurrencies))
                    } else {
                        callback.onResult(NowCryptoResult.Error(
                            message = response.message,
                            status = response.status
                        ))
                    }
                }
            } catch (e: Exception) {

                Log.e("CryptoDebug", "CRASH INSIDE COROUTINE!", e)

                withContext(Dispatchers.Main) {
                    val errorMessage = if (e is retrofit2.HttpException) {
                        // Parse the JSON error body manually
                        try {
                            val errorJson = e.response()?.errorBody()?.string()
                            val regex = """"message"\s*:\s*"([^"]+)"""".toRegex()
                            regex.find(errorJson ?: "")?.groupValues?.get(1) ?: "Error: ${e.code()}"
                        } catch (parseException: Exception) {
                            "HTTP Error: ${parseException.message}"
                        }
                    } else {
                        e.localizedMessage ?: "Network request failed"
                    }

                    callback.onResult(NowCryptoResult.Error(message = errorMessage, exception = e))
                }
            }
        }
    }

    @JvmStatic
    fun getSupportedCurrencies(context: Context, listener: NowCryptoListener<List<String>>) {
        getSupportedCurrencies(context) { result -> result.handle(listener) }
    }

    /**
     * Generate payment request token
     */
    @JvmStatic
    fun getPaymentRequestToken(
        context: Context,
        secretKey: String,
        amount: Double,
        currency: String,
        network: String,
        callback: NowCryptoCallback<String>
    ) {

        // Access internal dependencies
        val internal = NowCryptoInternal.getInstance(context)
        val paymentRequestTokenUseCase = internal.paymentRequestTokenUseCase

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = paymentRequestTokenUseCase.execute(secretKey, amount, currency, network)

                withContext(Dispatchers.Main) {
                    if (response.success) {
                        callback.onResult(NowCryptoResult.Success(response.paymentRequestToken))
                    } else {
                        callback.onResult(NowCryptoResult.Error(
                            message = response.message,
                            status = response.status
                        ))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    val errorMessage = if (e is retrofit2.HttpException) {
                        // Parse the JSON error body manually
                        try {
                            val errorJson = e.response()?.errorBody()?.string()
                            val regex = """"message"\s*:\s*"([^"]+)"""".toRegex()
                            regex.find(errorJson ?: "")?.groupValues?.get(1) ?: "Error: ${e.code()}"
                        } catch (parseException: Exception) {
                            "HTTP Error: ${parseException.message}}"
                        }
                    } else {
                        e.localizedMessage ?: "Network request failed"
                    }

                    callback.onResult(NowCryptoResult.Error(message = errorMessage, exception = e))
                }
            }
        }
    }

    @JvmStatic
    fun getPaymentRequestToken(context: Context, secretKey: String, amount: Double, currency: String, network: String, listener: NowCryptoListener<String>) {
        getPaymentRequestToken(context, secretKey, amount, currency, network) { result -> result.handle(listener) }
    }

    /**
     * Generate subscription payment request token
     */
    @JvmStatic
    fun getSubscriptionRequestToken(
        context: Context,
        secretKey: String,
        amount: Double,
        currency: String,
        network: String,
        subId: String,
        callback: NowCryptoCallback<String>
    ) {

        // Access internal dependencies
        val internal = NowCryptoInternal.getInstance(context)
        val subRequestTokenUseCase = internal.subRequestTokenUseCase

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = subRequestTokenUseCase.execute(secretKey, amount, currency, network, subId)

                withContext(Dispatchers.Main) {
                    if (response.success) {
                        callback.onResult(NowCryptoResult.Success(response.paymentRequestToken))
                    } else {
                        callback.onResult(NowCryptoResult.Error(
                            message = response.message,
                            status = response.status
                        ))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    val errorMessage = if (e is retrofit2.HttpException) {
                        // Parse the JSON error body manually
                        try {
                            val errorJson = e.response()?.errorBody()?.string()
                            val regex = """"message"\s*:\s*"([^"]+)"""".toRegex()
                            regex.find(errorJson ?: "")?.groupValues?.get(1) ?: "Error: ${e.code()}"
                        } catch (parseException: Exception) {
                            "HTTP Error: ${parseException.message}}"
                        }
                    } else {
                        e.localizedMessage ?: "Network request failed"
                    }

                    callback.onResult(NowCryptoResult.Error(message = errorMessage, exception = e))
                }
            }
        }
    }

    @JvmStatic
    fun getSubscriptionRequestToken(context: Context, secretKey: String, amount: Double, currency: String, network: String, subId: String, listener: NowCryptoListener<String>) {
        getSubscriptionRequestToken(context, secretKey, amount, currency, network, subId) { result -> result.handle(listener) }
    }

    /**
     * Get subscription list
     */
    @JvmStatic
    fun getSubscriptionList(
        context: Context,
        callback: NowCryptoCallback<List<NowCryptoSubscriptionItem>>
    ) {

        // Fail fast if the developer forgot to call NowCrypto.init()
        val publicKey = try {
            getRequiredPublicKey()
        } catch (e: IllegalStateException) {
            callback.onResult(NowCryptoResult.Error(message = e.message ?: "NowCrypto not initialized"))
            return
        }

        // Access internal dependencies
        val internal = NowCryptoInternal.getInstance(context)
        val subscriptionListUseCase = internal.subscriptionListUseCase

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = subscriptionListUseCase.execute(publicKey)

                withContext(Dispatchers.Main) {
                    if (response.success) {
                        callback.onResult(NowCryptoResult.Success(response.subscriptions))
                    } else {
                        callback.onResult(NowCryptoResult.Error(
                            message = response.message,
                            status = response.status
                        ))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    val errorMessage = if (e is retrofit2.HttpException) {
                        // Parse the JSON error body manually
                        try {
                            val errorJson = e.response()?.errorBody()?.string()
                            val regex = """"message"\s*:\s*"([^"]+)"""".toRegex()
                            regex.find(errorJson ?: "")?.groupValues?.get(1) ?: "Error: ${e.code()}"
                        } catch (parseException: Exception) {
                            "HTTP Error: ${parseException.message}}"
                        }
                    } else {
                        e.localizedMessage ?: "Network request failed"
                    }

                    callback.onResult(NowCryptoResult.Error(message = errorMessage, exception = e))
                }
            }
        }
    }

    @JvmStatic
    fun getSubscriptionList(context: Context, listener: NowCryptoListener<List<NowCryptoSubscriptionItem>>) {
        getSubscriptionList(context) { result -> result.handle(listener) }
    }

    /**
     * Get subscription list
     */
    @JvmStatic
    fun queryActiveSubscription(
        context: Context,
        callback: NowCryptoCallback<ActiveSubscriptionResult>
    ) {
        Log.d("CryptoDebug", "start")
        // Fail fast if the developer forgot to call NowCrypto.init()
        val publicKey = try {
            getRequiredPublicKey()
        } catch (e: IllegalStateException) {
            callback.onResult(NowCryptoResult.Error(message = e.message ?: "NowCrypto not initialized"))
            return
        }

        val internal = NowCryptoInternal.getInstance(context)
        val getQueryActiveSubscriptionUseCase = internal.queryActiveSubscriptionUseCase

        val deviceId = try {
            internal.deviceIdProvider.getDeviceId()
        } catch (e: Exception) {
            callback.onResult(NowCryptoResult.Error(message = e.message ?: "Device ID not found"))
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getQueryActiveSubscriptionUseCase.execute(publicKey, deviceId)
                Log.d("CryptoDebug", "Raw Response Object: $response")

                withContext(Dispatchers.Main) {
                    if (response.success) {
                        callback.onResult(
                            NowCryptoResult.Success(
                                ActiveSubscriptionResult(
                                    hasActiveSubscription = response.hasActiveSubscription,
                                    subscriptions = response.subscriptions
                                )
                            )
                        )
                    } else {
                        callback.onResult(NowCryptoResult.Error(
                            message = response.message,
                            status = response.status
                        ))
                    }
                }
            } catch (e: Exception) {
                Log.e("CryptoDebug", "CRASH INSIDE COROUTINE!", e)
                withContext(Dispatchers.Main) {
                    val errorMessage = if (e is retrofit2.HttpException) {
                        // Parse the JSON error body manually
                        try {
                            val errorJson = e.response()?.errorBody()?.string()
                            val regex = """"message"\s*:\s*"([^"]+)"""".toRegex()
                            regex.find(errorJson ?: "")?.groupValues?.get(1) ?: "Error: ${e.code()}"
                        } catch (parseException: Exception) {
                            "HTTP Error: ${parseException.message}}"
                        }
                    } else {
                        e.localizedMessage ?: "Network request failed"
                    }

                    callback.onResult(NowCryptoResult.Error(message = errorMessage, exception = e))
                }
            }
        }
    }

    @JvmStatic
    fun queryActiveSubscription(context: Context, listener: NowCryptoListener<ActiveSubscriptionResult>) {
        queryActiveSubscription(context) { result -> result.handle(listener) }
    }

    /**
     * Get payment status
     */
    @JvmStatic
    @JvmOverloads
    fun getPaymentStatus(
        context: Context,
        paymentRequestToken: String? = null,
        trxId: String? = null,
        callback: NowCryptoCallback<PaymentStatusResponse>
    ) {

        val publicKey = try {
            getRequiredPublicKey()
        } catch (e: IllegalStateException) {
            callback.onResult(NowCryptoResult.Error(message = e.message ?: "NowCrypto not initialized"))
            return
        }

        // Validate that at least one identifier exists
        if (paymentRequestToken.isNullOrBlank() && trxId.isNullOrBlank()) {
            callback.onResult(NowCryptoResult.Error("Either Payment Token or Transaction ID is required"))
            return
        }

        val internal = NowCryptoInternal.getInstance(context)
        val getPaymentStatusUseCase = internal.paymentStatusUseCase

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getPaymentStatusUseCase.execute(
                    publicKey = publicKey,
                    paymentRequestToken = paymentRequestToken,
                    trxId = trxId
                )

                withContext(Dispatchers.Main) {
                    if (response.success) {
                        callback.onResult(NowCryptoResult.Success(response))
                    } else {
                        callback.onResult(NowCryptoResult.Error(message = response.message))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Handle HTTP errors or Connection issues
                    callback.onResult(NowCryptoResult.Error(message = e.localizedMessage ?: "Failed to fetch status"))
                }
            }
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getPaymentStatus(context: Context, paymentRequestToken: String? = null, trxId: String? = null, listener: NowCryptoListener<PaymentStatusResponse>) {
        getPaymentStatus(context, paymentRequestToken, trxId) { result -> result.handle(listener) }
    }

    // Internal Notifiers for Activity Communication
    internal fun notifySuccess(transactionId: String) {
        paymentResultListener?.onSuccess(transactionId)
        paymentResultListener = null
    }

    internal fun notifyFailure(errorMessage: String) {
        paymentResultListener?.onFailure(errorMessage)
        paymentResultListener = null
    }

    internal fun notifyCancelled() {
        paymentResultListener?.onCancelled()
        paymentResultListener = null
    }

    /**
     * Internal helper to unwrap results into a listener.
     */
    private fun <T> NowCryptoResult<T>.handle(listener: NowCryptoListener<T>) {
        when (this) {
            is NowCryptoResult.Success -> listener.onSuccess(this.data)
            is NowCryptoResult.Error -> listener.onError(this.message)
            else -> {}
        }
    }
}
