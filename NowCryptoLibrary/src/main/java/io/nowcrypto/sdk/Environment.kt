package io.nowcrypto.sdk

import kotlinx.serialization.Serializable

@Serializable
public enum class Environment(public val value: String) {
    TEST("test"),
    LIVE("live")
}