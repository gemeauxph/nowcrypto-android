package io.nowcrypto.sdk.presentation.register_screen

import androidx.annotation.Keep

@Keep
sealed class RegisterUiState {

    @Keep
    object Idle : RegisterUiState()

    @Keep
    object RegisterLoading : RegisterUiState()

    @Keep
    data class RegisterSuccess(val response: String) : RegisterUiState()

    @Keep
    data class RegisterError(val message: String) : RegisterUiState()
}

