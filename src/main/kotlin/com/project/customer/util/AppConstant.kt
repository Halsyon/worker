package com.project.customer.util

data class AppConstant(
    val SUCCESS: String = "success",
    val FAILED: String = "failed"
) {
    companion object {
        val LOGGER = org.slf4j.LoggerFactory.getLogger(AppConstant::class.java)
        const val TAG = "AppConstant"
        const val SCHEMA_NAME = "worker"

    }
}