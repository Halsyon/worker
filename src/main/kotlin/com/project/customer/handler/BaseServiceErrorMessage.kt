package com.project.customer.handler

import com.fasterxml.jackson.annotation.JsonProperty

class BaseServiceErrorMessage(
    @JsonProperty("code")
    private var code: String? = null,
    @JsonProperty("message")
    private val message: String? = null,
    @JsonProperty("timestamp")
    private val timestamp: String? = null
) {
}