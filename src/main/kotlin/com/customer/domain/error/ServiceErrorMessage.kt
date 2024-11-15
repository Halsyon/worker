package com.customer.domain.error

import com.fasterxml.jackson.annotation.JsonProperty

class ServiceErrorMessage(
    @JsonProperty("code")
    private var code: String? = null,
    @JsonProperty("message")
    private val message: String? = null,
    @JsonProperty("timestamp")
    private val timestamp: String? = null
) {
}