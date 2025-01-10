package com.project.customer.api.dto.departmentbusinessevent

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.context.ApplicationEvent

@Tag(name = "DepartmentBusinessEvent", description = "The Department Business Event object")
data class DepartmentBusinessEvent(
    val sourceObject: Any,
    val payload: String,
) : ApplicationEvent(sourceObject) {
}
