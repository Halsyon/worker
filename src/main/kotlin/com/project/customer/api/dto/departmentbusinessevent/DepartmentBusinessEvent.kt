package com.project.customer.api.dto.departmentbusinessevent

import org.springframework.context.ApplicationEvent

data class DepartmentBusinessEvent(
    val sourceObject: Any,
    val payload: String,
) : ApplicationEvent(sourceObject) {
}
