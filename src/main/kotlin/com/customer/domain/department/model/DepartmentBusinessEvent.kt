package com.customer.domain.department.model

import org.springframework.context.ApplicationEvent

data class DepartmentBusinessEvent(
    val sourceObject: Any,
    val payload: String,
) : ApplicationEvent(sourceObject) {

}
