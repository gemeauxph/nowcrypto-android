package io.nowcrypto.sdk

import kotlinx.serialization.Serializable

/**
 * Marking as 'public' because of explicitApi mode.
 * Marking as '@Serializable' so it can be used in your Network Request/Response classes.
 */
@Serializable
public enum class Environment(public val value: String) {
    TEST("test"),
    LIVE("live")
}